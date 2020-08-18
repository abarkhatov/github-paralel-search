package com.abarkhatov.githubsearch.repository.remote.api


import com.abarkhatov.githubsearch.repository.remote.model.GithubResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GitHubApi {
    @GET("search/repositories")
    fun searchByName(@Query("q")  query:String, @Query("page") page:Int, @Query("per_page") perPage:Int): Call<GithubResponse>

    @GET("search/repositories")
    fun searchByNameRx(@Query("q")  query:String, @Query("page") page:Int, @Query("per_page") perPage:Int): Observable<GithubResponse>


}