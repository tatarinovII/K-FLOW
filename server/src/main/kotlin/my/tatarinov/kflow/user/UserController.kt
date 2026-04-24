package my.tatarinov.kflow.user

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import my.tatarinov.kflow.database.user.Users
import java.util.UUID

class UserController(
    private val call: ApplicationCall
) {

    suspend fun getUserData() {
        val principal = call.principal<JWTPrincipal>()
        val id = principal!!.payload.getClaim("id").asString()

        val user = Users.fetchUserById(UUID.fromString(id))

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