package example.com.models

data class UserModel (
    val id : Int,
    val fullName : String,
    val avatar : String,
    val email : String,
    var authToken : String? = null,
    var createdAt : String
)