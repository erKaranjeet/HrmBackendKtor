package example.com.repository

import example.com.services.CreateUserParams
import example.com.utils.BaseResponse

interface UserRepository {

    suspend fun registerUser(params: CreateUserParams) : BaseResponse<Any>

    suspend fun loginUser(email: String, password: String) : BaseResponse<Any>
}