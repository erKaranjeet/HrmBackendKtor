package example.com

import example.com.db.DatabaseFactory
import example.com.repository.UserRepository
import example.com.repository.UserRepositoryImpl
import example.com.routes.authRoutes
import example.com.security.configureSecurity
import example.com.services.UserService
import example.com.services.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()

    install(ContentNegotiation) {
        jackson()
    }

    configureSecurity()
    val userService : UserService = UserServiceImpl()
    val userRepository : UserRepository = UserRepositoryImpl(userService)
    authRoutes(userRepository)
}