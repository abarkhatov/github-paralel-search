package com.abarkhatov.githubsearch.mvp.contract


import com.abarkhatov.githubsearch.repository.local.entity.FavoriteRepo

interface FavoritesActivityContract {
    interface Presenter {

        fun delete(repo: FavoriteRepo)
        suspend fun getFavList():List<FavoriteRepo>
    }

    interface View {

        fun updateList(list: List<FavoriteRepo>)

    }
}