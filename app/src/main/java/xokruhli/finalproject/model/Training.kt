package xokruhli.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "trainings")
data class Training(


    @ColumnInfo(name = "trainingName")
    var name: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trainingId")
    var trainingId: Long? = null

    @ColumnInfo(name = "image")
    var image: String = ""
}