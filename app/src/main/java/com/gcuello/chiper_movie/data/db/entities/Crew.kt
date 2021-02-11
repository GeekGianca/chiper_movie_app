package com.gcuello.chiper_movie.data.db.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "crew")
data class Crew(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "job")
    var job: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "adult")
    var adult: Boolean?,
    @ColumnInfo(name = "gender")
    var gender: Int?,
    @ColumnInfo(name = "credit_id")
    var creditId: String?,
    @ColumnInfo(name = "department")
    var department: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Double?,
    @ColumnInfo(name = "profile_path")
    var profilePath: String?,
    @ColumnInfo(name = "original_name")
    var originalName: String?,
    @ColumnInfo(name = "known_for_department")
    var knownForDepartment: String?
)
