package xokruhli.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @ColumnInfo(name = "exerciseName")
    var name: String,

    @ColumnInfo(name = "reps")
    var reps: String,

    @ColumnInfo(name = "sets")
    var sets: String,

    @ColumnInfo(name = "weight")
    var weight: String,

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exerciseId")
    var exerciseId: Long? = null

    @ColumnInfo(name = "trainingId")
    var trainingId: Long? = null

}