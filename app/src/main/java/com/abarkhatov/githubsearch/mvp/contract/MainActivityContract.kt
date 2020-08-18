package com.abarkhatov.githubsearch.mvp.contract


import com.abarkhatov.githubsearch.repository.remote.model.Item

interface MainActivityContract {
    interface Presenter {

        fun searchNext(query: String)

        fun searchPrevious(query: String)

        fun search(query: String)

        fun addToDatabase(repo:Item)
    }

    interface View {

        fun updateList(list: List<Item>)
        fun showError(t:Throwable)


    }
}