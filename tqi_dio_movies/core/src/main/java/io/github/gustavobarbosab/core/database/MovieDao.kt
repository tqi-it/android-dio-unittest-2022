package io.github.gustavobarbosab.core.database

import androidx.room.*
import io.github.gustavobarbosab.core.database.dto.MovieDto

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    suspend fun getAll(): List<MovieDto>

    @Query("SELECT * FROM Movie WHERE favorite ORDER BY savedDate")
    suspend fun getFavorites(): List<MovieDto>

    @Query("SELECT * FROM Movie WHERE favorite AND id = :id")
    suspend fun getFavorite(id: Long): MovieDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg movieDto: MovieDto)

    @Query("UPDATE Movie SET favorite = 0 WHERE id = :id")
    suspend fun unlikeMovie(id: Long)
}