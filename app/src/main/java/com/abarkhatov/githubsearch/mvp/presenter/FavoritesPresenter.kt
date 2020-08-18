package com.abarkhatov.githubsearch.mvp.presenter

import com.abarkhatov.githubsearch.mvp.contract.FavoritesActivityContract
import com.abarkhatov.githubsearch.mvp.contract.IDatabase
import com.abarkhatov.githubsearch.repository.local.DatabaseManager
import com.abarkhatov.githubsearch.repository.local.entity.FavoriteRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch





class FavoritesPresenter(private val view: FavoritesActivityContract.View) :
    FavoritesActivityContract.Presenter {

    private val databaseManager: IDatabase = DatabaseManager()
    companion object {
        val TAG = FavoritesPresenter::class.java.simpleName
    }

    override fun delete(repo: FavoriteRepo) {
      GlobalScope.launch {
          databaseManager.removeFromFavorite(repo)
          view.updateList(databaseManager.getAllFavorites())

      }
    }

    override suspend fun getFavList(): List<FavoriteRepo> {
        return databaseManager.getAllFavorites()
    }


}