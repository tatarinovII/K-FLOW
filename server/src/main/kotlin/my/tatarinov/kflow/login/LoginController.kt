package my.tatarinov.kflow.login

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import my.tatarinov.kflow.database.user.Users
import my.tatarinov.kflow.utils.JwtConfig
import org.mindrot.jbcrypt.BCrypt

class LoginController(private val call: ApplicationCall) {

    suspend fun loginUser() {
        val receive = call.receive<LoginRequest>()

        val user = Users.fetchUserByEmail(receive.email)

        if (user == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else if (!BCrypt.checkpw(receive.password, user.password)) {
            call.respond(HttpStatusCode.BadRequest, "Invalid password")
        } else {
            val token = JwtConfig.generateToken(user.id.toString())
            call.respond(LoginResponse(token))
        }
    }
}