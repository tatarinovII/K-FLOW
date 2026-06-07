package my.tatarinov.kflow.data.repos

import my.tatarinov.kflow.data.api.HomeApi
import my.tatarinov.kflow.data.utils.Converter
import my.tatarinov.kflow.domain.models.UpcomingWorkout
import my.tatarinov.kflow.domain.models.User
import my.tatarinov.kflow.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val converter: Converter,
    private val api: HomeApi
): HomeRepository {

    override suspend fun getUserData(): Result<User> {
        val response = api.getUser()
        return response.fold(
            onSuccess = {
                Result.success(converter.map(it))
            },
            onFailure = {
                Result.failure(Exception("Error while getting User data"))
            }
        )
    }

    override suspend fun getUpcomingWorkouts(): Result<List<UpcomingWorkout>> {
        val response = api.getUpcomingWorkouts()
        return response.fold(
            onSuccess = {
                Result.success(it.workouts.map { workout ->
                    converter.map(workout)
                })
            },
            onFailure = {
                Result.failure(Exception("Error while getting Upcoming workouts"))
            }
        )
    }

    override suspend fun bookWorkout(id: String): Result<Unit> {
        return api.bookWorkout(id)
    }

    override suspend fun deleteBooking(id: String): Result<Unit> {
        return api.deleteBooking(id)
    }

}