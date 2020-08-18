package com.abarkhatov.githubsearch.repository.remote

import com.abarkhatov.githubsearch.MyApplication
import com.abarkhatov.githubsearch.mvp.contract.INetwork
import com.abarkhatov.githubsearch.repository.remote.api.GitHubApi
import com.abarkhatov.githubsearch.repository.remote.model.GithubResponse
import com.abarkhatov.githubsearch.repository.remote.model.Item
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject
import kotlin.collections.ArrayList

const val RESULTS_PER_PAGE = 15

class NetworkManager(private val callback: INetwork.ICallback) : INetwork {

    companion object {
        val TAG = NetworkManager::class.java.name
    }

    @Inject
    lateinit var serverApi: GitHubApi

    init {
        MyApplication.getComponent()?.inject(this)
    }




    override fun searchRx(query: String, page: Int) {
        val first = serverApi.searchByNameRx(
            query, page,
            RESULTS_PER_PAGE
        )
            .doOnError { callback.handleError(it) }
            .map { resp -> resp.items }
            .onErrorReturn { ArrayList() }
        val second = serverApi.searchByNameRx(
            query, page + 1,
            RESULTS_PER_PAGE
        )

            .doOnError { callback.handleError(it) }
            .map { resp -> resp.items }
            .onErrorReturn { ArrayList() }
        val result = Observable.zip(
            first,
            second,
            BiFunction<List<Item>, List<Item>, List<Item>> { firstList, secondList ->
                return@BiFunction margeLists(firstList, secondList)
            })
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list -> callback.handleResults(list) }
    }

    /* fun handleResults(list:List<Item>){
        for (item:Item in list){
            Log.e("tag",item.full_name)
        }
    }*/
    private fun margeLists(
        firstList: List<Item>,
        secondList: List<Item>
    ): List<Item> {
        val resList = ArrayList<Item>()
        resList.addAll(firstList)
        resList.addAll(secondList)
        return resList

    }

}