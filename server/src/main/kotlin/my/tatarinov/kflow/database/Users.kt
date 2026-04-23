package my.tatarinov.kflow.database

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object Users : Table("users") {
    private val email = varchar("email", 50)
    private val password = varchar("password", 100)
    private val firstName = varchar("firstName", 25)
    private val lastName = varchar("lastName", 25)
    private val sex = varchar("sex", 10)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[email] = userDTO.email
                it[password] = userDTO.password
                it[firstName] = userDTO.firstName
                it[lastName] = userDTO.lastName
                it[sex] = userDTO.sex
            }
        }
    }

    fun fetchUserByEmail(email: String): UserDTO? {
        return transaction {
            val user = Users.selectAll().where(Users.email eq email).singleOrNull()
            if (user != null) {
                UserDTO(
                    email = user[Users.email],
                    password = user[password],
                    firstName = user[firstName],
                    lastName = user[lastName],
                    sex = user[sex]
                )
            } else null
        }
    }
}