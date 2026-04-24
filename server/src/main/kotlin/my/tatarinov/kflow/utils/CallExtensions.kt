package my.tatarinov.kflow.utils

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import java.util.UUID

suspend fun io.ktor.server.application.ApplicationCall.userIdOrRespond(): UUID? {
    val principal = principal<JWTPrincipal>()
    val idString = principal?.payload?.getClaim("id")?.asString()
    val userId = idString?.let { runCatching { UUID.fromString(it) }.getOrNull() }
    if (userId == null) {
        respond(HttpStatusCode.Unauthorized, "Invalid token")
        return null
    }
    return userId
}