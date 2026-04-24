package my.tatarinov.kflow.database.workout


import java.time.LocalDateTime
import java.util.UUID

data class WorkoutEntity(
    val id: UUID,
    val title: String,
    val startsAt: LocalDateTime,
    val capacity: Int
)