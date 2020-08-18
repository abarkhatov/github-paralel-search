package com.abarkhatov.githubsearch.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.abarkhatov.githubsearch.R
import com.abarkhatov.githubsearch.repository.local.entity.FavoriteRepo
import com.abarkhatov.githubsearch.repository.remote.model.Item


class GitHubSearchAdapter(var mSearchResultsList: List<Item>, val callback: FavoriteClickCallback) :
    RecyclerView.Adapter<GitHubSearchAdapter.SearchResultViewHolder>( ) {


    fun updateSearchResults(searchResultsList: List<Item>) {
        mSearchResultsList = searchResultsList
        notifyDataSetChanged()
    }

    override fun getItemCount() =  mSearchResultsList.size

    @NonNull
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.search_result_item, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SearchResultViewHolder,
        position: Int
    ) {
        holder.bind(mSearchResultsList[position], callback)
    }

      class SearchResultViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val mSearchResultTV: TextView
          private val favoritesIcon: ImageView
        fun bind(repo: Item, callback: FavoriteClickCallback) {
            mSearchResultTV.text = repo.full_name
            favoritesIcon.setImageResource(android.R.drawable.star_off)
            favoritesIcon.setOnClickListener {
                callback.favoriteClick(repo)
            }
        }

        init {
            mSearchResultTV = itemView.findViewById(R.id.tv_search_result)
            favoritesIcon = itemView.findViewById(R.id.iv_favorite)
        }
    }

    interface FavoriteClickCallback {
        fun favoriteClick(repo: Item)

    }
}