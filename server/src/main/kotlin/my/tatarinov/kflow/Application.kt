package my.tatarinov.kflow

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
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

    println("=== ENV DEBUG ===")
    println("DATABASE_URL: ${System.getenv("DATABASE_URL")}")
    println("PGHOST: ${System.getenv("PGHOST")}")
    println("PGUSER: ${System.getenv("PGUSER")}")
    println("PGPASSWORD: ${System.getenv("PGPASSWORD")}")
    println("PGPORT: ${System.getenv("PGPORT")}")
    println("PGDATABASE: ${System.getenv("PGDATABASE")}")
    println("=================")

    val rawUrl =
        System.getenv("DATABASE_URL") ?: throw IllegalArgumentException("DATABASE_URL not set")

    val uri = java.net.URI(rawUrl)
    val jdbcUrl = "jdbc:postgresql://${uri.host}:${uri.port}${uri.path}"
    val user = uri.userInfo.split(":")[0]
    val password = uri.userInfo.split(":")[1]

    Database.connect(
        url = jdbcUrl, driver = "org.postgresql.Driver", user = user, password = password
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Users)
    }

    embeddedServer(
        Netty,
        port = System.getenv("PORT")?.toInt() ?: 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
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