package com.abarkhatov.githubsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abarkhatov.githubsearch.adapter.FavoritesAdapter
import com.abarkhatov.githubsearch.mvp.contract.FavoritesActivityContract
import com.abarkhatov.githubsearch.mvp.presenter.FavoritesPresenter
import com.abarkhatov.githubsearch.repository.local.entity.FavoriteRepo
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritesActivity : AppCompatActivity(), FavoritesActivityContract.View,
    FavoritesAdapter.FavoriteClickCallback {


    lateinit var presenter: FavoritesPresenter
    lateinit var favoritesAdapter: FavoritesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        initPresenter()
        back.setOnClickListener {
            finish()
        }


    }

    private fun initAdapter() {
        favoritesAdapter = FavoritesAdapter(ArrayList(), this)
        rv_favorite.adapter = favoritesAdapter
        GlobalScope.launch {
            val list = presenter.getFavList()
            updateList(list)
        }
    }

    private fun initPresenter() {
        presenter = FavoritesPresenter(this)

    }

    override fun updateList(list: List<FavoriteRepo>) {
        runOnUiThread { favoritesAdapter.updateFavoritesList(list) }
    }

    override fun onResume() {
        super.onResume()
        initAdapter()
    }

    override fun favoriteClick(repo: FavoriteRepo) {
        presenter.delete(repo)
    }

}