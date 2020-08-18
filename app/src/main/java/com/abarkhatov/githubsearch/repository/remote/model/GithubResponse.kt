package com.abarkhatov.githubsearch.repository.remote.model

data class GithubResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)