package com.abarkhatov.githubsearch.repository.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.abarkhatov.githubsearch.repository.local.entity.FavoriteRepo

@Dao
interface FavoriteRepositoryDao {

    @Insert(onConflict = IGNORE)
    suspend fun insertRepo(repo: FavoriteRepo)





    @Query("DELETE FROM favorite_repo WHERE html_url == :url")
    suspend fun deleteRepo(url: String)


    @Query("select * from favorite_repo")
    suspend fun getAllFavorites(): List<FavoriteRepo>


}
