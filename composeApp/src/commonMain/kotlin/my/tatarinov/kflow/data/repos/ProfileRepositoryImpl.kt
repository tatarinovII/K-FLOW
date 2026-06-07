package my.tatarinov.kflow.data.repos

import my.tatarinov.kflow.data.api.ProfileApi
import my.tatarinov.kflow.data.utils.Converter
import my.tatarinov.kflow.domain.models.PastWorkout
import my.tatarinov.kflow.domain.models.User
import my.tatarinov.kflow.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val converter: Converter, private val api: ProfileApi
) : ProfileRepository {
    override suspend fun getUserData(): Result<User> {
        val response = api.getUser()
        return response.fold(onSuccess = {
            Result.success(converter.map(it))
        }, onFailure = {
            Result.failure(Exception("Error while getting User data"))
        })
    }

    override suspend fun getWorkoutsHistory(): Result<List<PastWorkout>> {
        val response = api.getPastWorkouts()
        return response.fold(onSuccess = {
            Result.success(it.workouts.map { workout ->
                converter.map(workout)
            })
        }, onFailure = {
            Result.failure(Exception("Error while getting Past workouts"))
        })
    }

}