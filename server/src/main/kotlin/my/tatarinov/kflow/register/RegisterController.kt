package my.tatarinov.kflow.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import my.tatarinov.kflow.database.user.NewUser
import my.tatarinov.kflow.database.user.Users
import my.tatarinov.kflow.utils.JwtConfig
import org.mindrot.jbcrypt.BCrypt

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerUser() {

        val receive = call.receive<RegisterRequest>()

        val user = Users.fetchUserByEmail(receive.email)

        if (user != null) {
            call.respond(HttpStatusCode.Conflict, "User with this email already exists")
        } else {
            if (receive.password.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Password is required")
                return
            }
            val hashedPassword = BCrypt.hashpw(receive.password, BCrypt.gensalt())
            val id = Users.insert(
                NewUser(
                    email = receive.email,
                    password = hashedPassword,
                    firstName = receive.firstName,
                    lastName = receive.lastName,
                    sex = receive.sex
                )
            )
            val token = JwtConfig.generateToken(id.toString())

            call.respond(RegisterResponse(token))
        }


    }
}