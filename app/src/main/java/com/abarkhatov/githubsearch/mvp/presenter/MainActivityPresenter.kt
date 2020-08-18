package com.abarkhatov.githubsearch.mvp.presenter

import com.abarkhatov.githubsearch.MyApplication
import com.abarkhatov.githubsearch.mvp.contract.IDatabase
import com.abarkhatov.githubsearch.mvp.contract.INetwork
import com.abarkhatov.githubsearch.mvp.contract.MainActivityContract
import com.abarkhatov.githubsearch.repository.local.DatabaseManager
import com.abarkhatov.githubsearch.repository.remote.NetworkManager
import com.abarkhatov.githubsearch.repository.remote.api.GitHubApi
import com.abarkhatov.githubsearch.repository.remote.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivityPresenter(private val view: MainActivityContract.View) :
    MainActivityContract.Presenter, INetwork.ICallback {

    private var resultList: MutableList<Item> = ArrayList()
    private var page = 1
    companion object {
        val TAG = MainActivityPresenter::class.java.simpleName
    }

    private val network: INetwork = NetworkManager(this)
    private val databaseManager: IDatabase = DatabaseManager()

    @Inject
    protected lateinit var remoteApi: GitHubApi

    init {
        MyApplication.getComponent()?.inject(this)

    }

    override fun searchNext(query: String) {
        page++
        search(query)
    }

    override fun searchPrevious(query: String) {
        if (page > 1)
            page--
        search(query)

    }

    override fun search(query: String) {

        resultList.clear()
        network.searchRx(query, page)

    }

    override fun addToDatabase(repo: Item) {
        GlobalScope.launch {
            databaseManager.addFavorite(repo)
        }
    }




    override fun handleResults(list: List<Item>) {
        resultList.addAll(list)
        view.updateList(resultList)
    }

    override fun handleError(t: Throwable) {
        view.showError(t)
    }


}