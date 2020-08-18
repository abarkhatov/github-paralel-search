package com.abarkhatov.githubsearch.mvp.contract

import com.abarkhatov.githubsearch.repository.remote.model.Item


interface INetwork {

    interface ICallback {
        fun handleResults(list:List<Item>)
        fun handleError(t:Throwable)
    }
    fun searchRx(query: String, page: Int)
}