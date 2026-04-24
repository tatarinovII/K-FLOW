package my.tatarinov.kflow.database.user

import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.util.UUID

object Users : UUIDTable("user") {
    val email = varchar("email", 50).uniqueIndex()
    val password = varchar("password", 100)
    val firstName = varchar("firstName", 25)
    val lastName = varchar("lastName", 25)
    val sex = varchar("sex", 10)
    val workoutsRemain = integer("workoutsRemain").default(0)


    suspend fun insert(userDTO: NewUser): UUID {
        return suspendTransaction {
            Users.insertAndGetId {
                it[email] = userDTO.email
                it[password] = userDTO.password
                it[firstName] = userDTO.firstName
                it[lastName] = userDTO.lastName
                it[sex] = userDTO.sex
            }.value
        }
    }

    suspend fun fetchUserByEmail(email: String): UserEntity? {
        return suspendTransaction {
            val user = Users.selectAll().where(Users.email eq email).singleOrNull()
            if (user != null) {
                UserEntity(
                    email = user[Users.email],
                    password = user[password],
                    firstName = user[firstName],
                    lastName = user[lastName],
                    sex = user[sex],
                    workoutsRemain = user[workoutsRemain],
                    id = user[Users.id].value
                )
            } else null
        }
    }

    suspend fun fetchUserById(id: UUID): UserEntity? {
        return suspendTransaction {
            val user = Users.selectAll().where(Users.id eq id).singleOrNull()
            if (user != null) {
                UserEntity(
                    email = user[Users.email],
                    password = user[password],
                    firstName = user[firstName],
                    lastName = user[lastName],
                    sex = user[sex],
                    workoutsRemain = user[workoutsRemain],
                    id = user[Users.id].value
                )
            } else null
        }
    }
}