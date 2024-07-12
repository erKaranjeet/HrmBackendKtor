package example.com.services

import example.com.models.UserModel

interface UserService {

    suspend fun registerUser(params: CreateUserParams) : UserModel?

    suspend fun findUserByEmail(email: String) : UserModel?
}