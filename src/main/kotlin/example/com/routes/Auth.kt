package example.com.routes

import example.com.repository.UserRepository
import example.com.services.CreateUserParams
import example.com.services.FindUserParams
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(repository: UserRepository) {

    routing {
        route("/auth") {
            post("/register") {
                val params = call.receive<CreateUserParams>()
                val result = repository.registerUser(params)
                call.respond(result.statusCode, result)
            }

            get("/login") {
                val params = call.receive<FindUserParams>()
                val result = repository.loginUser(params.email, params.password)
                call.respond(result.statusCode, result)
            }
        }

        //Test Token
        authenticate {
            get("/test-token") {
                call.respond("Token worked.")
            }
        }
    }
}