package example.com.security

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    JWTConfig.initialize("hrm-app")
    install(Authentication) {
        jwt {
            verifier(JWTConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JWTConfig.CLAIM).asInt()
                if (claim != null) {
                    UserIdPrincipalForUser(claim)
                } else {
                    null
                }
            }
        }
    }
}