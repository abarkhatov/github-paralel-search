package com.abarkhatov.githubsearch.mvp.contract

import com.abarkhatov.githubsearch.repository.local.entity.FavoriteRepo
import com.abarkhatov.githubsearch.repository.remote.model.GithubResponse
import com.abarkhatov.githubsearch.repository.remote.model.Item
import retrofit2.Call
import retrofit2.Response

interface IDatabase {

    suspend fun getAllFavorites(): List<FavoriteRepo>

    suspend fun addFavorite(repo: Item)

    suspend fun removeFromFavorite(repo: FavoriteRepo)

}