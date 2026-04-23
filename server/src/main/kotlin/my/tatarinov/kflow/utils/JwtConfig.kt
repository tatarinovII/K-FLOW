package my.tatarinov.kflow.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import java.util.*

object JwtConfig {

    private val SECRET = System.getenv("JWT_SECRET")
        ?: throw IllegalArgumentException("JWT_SECRET not set")
    const val ISSUER = "kflow"
    const val AUDIENCE = "kflow-users"
    const val REALM = "kflow"

    private val algorithm = Algorithm.HMAC256(SECRET)

    fun generateToken(email: String): String {
        return JWT.create()
            .withIssuer(ISSUER)
            .withAudience(AUDIENCE)
            .withClaim("email", email)
            .withExpiresAt(
                Date(System.currentTimeMillis() + 3600000)
            )
            .sign(algorithm)
    }

    fun configureAuth(app: Application) {
        app.install(Authentication) {
            jwt("auth-jwt") {
                realm = REALM
                verifier(
                    JWT.require(algorithm)
                        .withAudience(AUDIENCE)
                        .withIssuer(ISSUER)
                        .build()
                )
                validate { credential ->
                    if (credential.payload.getClaim("email").asString() != null) {
                        JWTPrincipal(credential.payload)
                    } else null
                }
                challenge { _, _ ->
                    call.respond(
                        HttpStatusCode.Unauthorized,
                        "Token is invalid or expired"
                    )
                }
            }
        }
    }
}