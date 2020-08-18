package com.abarkhatov.githubsearch.repository.remote.model

data class License(
    val key: String,
    val name: String,
    val node_id: String,
    val spdx_id: String,
    val url: String
)