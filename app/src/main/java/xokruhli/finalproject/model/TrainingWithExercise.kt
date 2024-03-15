package xokruhli.finalproject.model

import androidx.room.Embedded
import androidx.room.Relation

data class TrainingWithExercise(
    @Embedded val training: Training,
    @Relation(
        entity = Exercise::class,
        parentColumn = "trainingId",
        entityColumn = "trainingId",
    )
    val exercises: List<Exercise>
)