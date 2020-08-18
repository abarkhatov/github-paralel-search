package com.abarkhatov.githubsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abarkhatov.githubsearch.adapter.GitHubSearchAdapter
import com.abarkhatov.githubsearch.mvp.contract.MainActivityContract
import com.abarkhatov.githubsearch.mvp.presenter.MainActivityPresenter
import com.abarkhatov.githubsearch.repository.remote.model.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View,
    GitHubSearchAdapter.FavoriteClickCallback {

    lateinit var presenter: MainActivityPresenter
    lateinit var searchAdapter: GitHubSearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        iv_favorite.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initPresenter() {
        presenter = MainActivityPresenter(this)

    }

    private fun initSearch() {
        searchAdapter = GitHubSearchAdapter(ArrayList(), this)
        rv_search.adapter = searchAdapter

        searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                presenter.search(query)
                return false
            }
        })
        nextButton.setOnClickListener { presenter.searchNext(searchView.query.toString()) }
        prevButton.setOnClickListener { presenter.searchNext(searchView.query.toString()) }

    }

    override fun onResume() {
        super.onResume()
        initSearch()
    }

    override fun updateList(list: List<Item>) {
        searchAdapter.updateSearchResults(list)
    }

    override fun showError(t: Throwable) {
       runOnUiThread{ Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()}
    }

    override fun favoriteClick(repo: Item) {
        presenter.addToDatabase(repo)
    }
}

