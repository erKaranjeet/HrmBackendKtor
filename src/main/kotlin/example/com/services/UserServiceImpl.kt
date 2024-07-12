package example.com.services

import example.com.db.DatabaseFactory.dbQuery
import example.com.db.UserTable
import example.com.models.UserModel
import example.com.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
    override suspend fun registerUser(params: CreateUserParams): UserModel? {
        var statement : InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = hash(params.password)
                it[fullName] = params.fullName
                it[avatar] = params.avatar
            }
        }

        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): UserModel? {
        val user = dbQuery {
            UserTable.select {
                UserTable.email.eq(email)
            }
                .map {
                    rowToUser(it)
                }.singleOrNull()
        }

        return user
    }

    private fun rowToUser(row: ResultRow?) : UserModel? {
        return  if (row == null) null
        else UserModel(
            id = row[UserTable.id],
            fullName = row[UserTable.fullName],
            email = row[UserTable.email],
            avatar = row[UserTable.avatar],
            createdAt = row[UserTable.createdAt].toString(),
        )
    }
}