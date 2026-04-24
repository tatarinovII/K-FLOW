package my.tatarinov.kflow

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import my.tatarinov.kflow.database.booking.Booking
import my.tatarinov.kflow.database.user.Users
import my.tatarinov.kflow.database.workout.Workouts
import my.tatarinov.kflow.login.configureLoginRouting
import my.tatarinov.kflow.plugins.configureSerialization
import my.tatarinov.kflow.register.configureRegisterRouting
import my.tatarinov.kflow.user.configureUserRouting
import my.tatarinov.kflow.utils.JwtConfig
import my.tatarinov.kflow.workout.configureWorkoutRouting
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    connectToDatabase()
    startServer()
}

fun connectToDatabase() {
    val rawUrl = System.getenv("DATABASE_URL")
        ?: throw IllegalArgumentException("DATABASE_URL not set")

    val uri = java.net.URI(rawUrl)
    val (user, password) = uri.userInfo.split(":")

    Database.connect(
        url = "jdbc:postgresql://${uri.host}:${uri.port}${uri.path}",
        driver = "org.postgresql.Driver",
        user = user,
        password = password
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Users, Workouts, Booking)
    }
}

fun startServer() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            call.respond(mapOf("status" to "ok"))
        }
    }
    JwtConfig.configureAuth(this)
    configureSerialization()
    configureUserRouting()
    configureRegisterRouting()
    configureLoginRouting()
    configureWorkoutRouting()
}