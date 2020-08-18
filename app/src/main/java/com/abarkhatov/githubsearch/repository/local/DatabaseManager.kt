package com.abarkhatov.githubsearch.repository.local

import android.util.Log
import com.abarkhatov.githubsearch.MyApplication
import com.abarkhatov.githubsearch.mvp.contract.IDatabase
import com.abarkhatov.githubsearch.mvp.presenter.FavoritesPresenter
import com.abarkhatov.githubsearch.repository.local.entity.FavoriteRepo
import com.abarkhatov.githubsearch.repository.remote.model.Item
import javax.inject.Inject

class DatabaseManager : IDatabase {

    @Inject
    protected lateinit var database: AppDatabase

    init {
        MyApplication.getComponent()?.inject(this)
    }

    override suspend fun getAllFavorites(): List<FavoriteRepo> {
        return database.favoriteRepositoryDao().getAllFavorites()
    }

    override suspend fun addFavorite(repo: Item) {
        Log.d(TAG,"full_name ${repo.full_name}")
        database.favoriteRepositoryDao().insertRepo(
            FavoriteRepo(
                repo.full_name,
                repo.html_url,
                repo.stargazers_count
            )
        )
    }

    override suspend fun removeFromFavorite(repo: FavoriteRepo) {
        database.favoriteRepositoryDao().deleteRepo(repo.html_url)
    }

    companion object {
        val TAG = DatabaseManager::class.java.simpleName
    }
}