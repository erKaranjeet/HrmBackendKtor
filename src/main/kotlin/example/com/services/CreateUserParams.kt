package example.com.services

data class CreateUserParams (
    val fullName : String,
    val email : String,
    val password : String,
    val avatar : String
)