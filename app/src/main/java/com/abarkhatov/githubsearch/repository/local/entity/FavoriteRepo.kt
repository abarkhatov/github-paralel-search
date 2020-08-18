package com.abarkhatov.githubsearch.repository.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_repo")
data class FavoriteRepo(
    @ColumnInfo(name = "full_name")
    @PrimaryKey(autoGenerate = false)
    val full_name: String,
    @ColumnInfo(name = "html_url")
    val html_url: String,
    @ColumnInfo(name = "stargazers_count")
    val stargazers_count: Int
)


