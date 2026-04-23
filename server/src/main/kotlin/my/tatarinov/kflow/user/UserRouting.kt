package my.tatarinov.kflow.user

import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureUserRouting() {
    routing {
        authenticate("auth-jwt") {
            get("/user") {
                val controller = UserController(call)
                controller.getUserData()
            }
        }
    }
}