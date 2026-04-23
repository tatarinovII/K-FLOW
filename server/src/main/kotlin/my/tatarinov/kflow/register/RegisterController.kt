package my.tatarinov.kflow.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import my.tatarinov.kflow.database.UserDTO
import my.tatarinov.kflow.database.Users
import my.tatarinov.kflow.utils.JwtConfig
import org.mindrot.jbcrypt.BCrypt

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerUser() {

        val receive = call.receive<RegisterReceiveRemote>()

        val user = Users.fetchUserByEmail(receive.email)

        if (user != null) {
            call.respond(HttpStatusCode.Conflict, "User with this email already exists")
        } else {
            val token = JwtConfig.generateToken(receive.email)
            val hashedPassword = if (receive.password != null) BCrypt.hashpw(receive.password, BCrypt.gensalt()) else ""
            Users.insert(
                UserDTO(
                    email = receive.email,
                    password = hashedPassword,
                    firstName = receive.firstName,
                    lastName = receive.lastName,
                    sex = receive.sex,
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }


    }
}