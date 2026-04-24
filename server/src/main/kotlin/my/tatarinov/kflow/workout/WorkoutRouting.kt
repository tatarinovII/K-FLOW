package my.tatarinov.kflow.workout

import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import my.tatarinov.kflow.utils.userIdOrRespond

fun Application.configureWorkoutRouting() {
    routing {
        authenticate("auth-jwt") {
            route("/workouts") {

                get("/upcoming") {
                    val userId = call.userIdOrRespond() ?: return@get
                    WorkoutController(call).getUpcoming(userId)
                }

                post("/{id}/bookings") {
                    val userId = call.userIdOrRespond() ?: return@post
                    WorkoutController(call).book(userId)
                }

                delete("/{id}/bookings") {
                    val userId = call.userIdOrRespond() ?: return@delete
                    WorkoutController(call).cancel(userId)
                }

                get("/history") {
                    val userId = call.userIdOrRespond() ?: return@get
                    WorkoutController(call).getHistory(userId)
                }
            }
        }
    }
}