package my.tatarinov.kflow

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import my.tatarinov.kflow.database.Users
import my.tatarinov.kflow.login.configureLoginRouting
import my.tatarinov.kflow.plugins.configureSerialization
import my.tatarinov.kflow.register.configureRegisterRouting
import my.tatarinov.kflow.user.configureUserRouting
import my.tatarinov.kflow.utils.JwtConfig
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    Database.connect(
        url = System.getenv("DB_URL") ?: throw IllegalArgumentException("DB_URL not set"),
        driver = "org.postgresql.Driver",
        user = System.getenv("DB_USER") ?: throw IllegalArgumentException("DB_USER not set")
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Users)
    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
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
}