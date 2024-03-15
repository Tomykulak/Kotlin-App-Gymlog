package xokruhli.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "gender")
    var gender: Boolean,

    @ColumnInfo(name = "weight")
    var weight: Int,

    @ColumnInfo(name = "height")
    var height: Int,
    ) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long? = null
    }
