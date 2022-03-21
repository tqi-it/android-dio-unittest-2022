package io.github.gustavobarbosab.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.gustavobarbosab.core.database.MoovieDataBase.Companion.DB_VERSION
import io.github.gustavobarbosab.core.database.dto.MovieDto

@Database(entities = [MovieDto::class], version = DB_VERSION)
abstract class MoovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val DB_VERSION = 1
    }
}