package my.tatarinov.kflow.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import my.tatarinov.kflow.data.dto.home.UpcomingWorkoutsResponse
import my.tatarinov.kflow.data.dto.home.UserResponse
import my.tatarinov.kflow.data.storage.TokenStorage

class HomeApi(
    private val client: HttpClient,
    private val storage: TokenStorage
) {

    suspend fun getUser(): Result<UserResponse> = runCatching {
        val token = storage.getToken() ?: error("No auth token")

        client.get("user") {
            bearerAuth(token)
        }.body<UserResponse>()
    }

    suspend fun getUpcomingWorkouts(): Result<UpcomingWorkoutsResponse> = runCatching {
        val token = storage.getToken() ?: error("No auth token")

        client.get("workouts/upcoming") {
            bearerAuth(token)
        }.body<UpcomingWorkoutsResponse>()
    }

    suspend fun bookWorkout(id: String): Result<Unit> = runCatching {
        val token = storage.getToken() ?: error("No auth token")

        client.post("workouts/$id/bookings") {
            bearerAuth(token)
        }
    }

    suspend fun deleteBooking(id: String): Result<Unit> = runCatching {
        val token = storage.getToken() ?: error("No auth token")

        client.delete("workouts/$id/bookings") {
            bearerAuth(token)
        }
    }

}