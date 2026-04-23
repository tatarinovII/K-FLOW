package my.tatarinov.kflow.user

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import my.tatarinov.kflow.database.Users

class UserController(
    private val call: ApplicationCall
) {

    suspend fun getUserData() {
        val principal = call.principal<JWTPrincipal>()
        val email = principal!!.payload.getClaim("email").asString()

        val user = Users.fetchUserByEmail(email)

        if (user == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
            return
        }

        call.respond(
            UserResponse(
                email = user.email,
                firstName = user.firstName,
                lastName = user.lastName,
                sex = user.sex
            )
        )
    }
}