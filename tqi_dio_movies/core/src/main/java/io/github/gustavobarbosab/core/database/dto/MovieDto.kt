package io.github.gustavobarbosab.core.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieDto(
    @PrimaryKey val id: Long,
    val name: String,
    val imageUrl: String,
    val posterUrl: String,
    val description: String,
    val savedDate: String,
    val favorite: Boolean = false
)