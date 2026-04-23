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

    val rawUrl = System.getenv("DATABASE_URL")
        ?: throw IllegalArgumentException("DATABASE_URL not set")

    val uri = java.net.URI(rawUrl)
    val jdbcUrl = "jdbc:postgresql://${uri.host}:${uri.port}${uri.path}"

    val userInfo = uri.userInfo?.split(":")
    val user = userInfo?.getOrNull(0)
        ?: System.getenv("PGUSER")
        ?: throw IllegalArgumentException("No DB user found")
    val password = userInfo?.getOrNull(1)
        ?: System.getenv("PGPASSWORD")
        ?: ""

    Database.connect(
        url = jdbcUrl,
        driver = "org.postgresql.Driver",
        user = user,
        password = password
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