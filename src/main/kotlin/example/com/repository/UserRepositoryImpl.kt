package example.com.repository

import example.com.security.JWTConfig
import example.com.services.CreateUserParams
import example.com.services.UserService
import example.com.utils.BaseResponse

class UserRepositoryImpl(private val service : UserService) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = "Email ID already exist")
        } else {
            val user = service.registerUser(params)
            if (user != null ) {
                val token = JWTConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user)
            } else {
                BaseResponse.ErrorResponse()
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        return if (!isEmailExist(email)) {
            BaseResponse.ErrorResponse(message = "Email ID not exist!")
        } else {
            val user = service.findUserByEmail(email)
            if (user != null ) {
                val token = JWTConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user)
            } else {
                BaseResponse.ErrorResponse()
            }
        }
    }

    private suspend fun isEmailExist(email : String) : Boolean {
        return service.findUserByEmail(email) != null
    }
}