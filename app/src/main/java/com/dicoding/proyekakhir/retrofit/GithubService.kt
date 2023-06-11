package com.dicoding.proyekakhir.retrofit

import com.dicoding.proyekakhir.BuildConfig.GITHUB_TOKEN
import com.dicoding.proyekakhir.model.data.GithubUser
import com.dicoding.proyekakhir.model.data.GithubUserArray
import com.dicoding.proyekakhir.model.data.GithubUserDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchEndpoint(@Query("q") searchQuery: String): Call<GithubUserArray>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetailsEndpoint(@Path("username") userDetails: String): Call<GithubUserDetails>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowersEndpoint(@Path("username") userFollowers: String): Call<ArrayList<GithubUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowingEndpoint(@Path("username") userFollowing: String): Call<ArrayList<GithubUser>>

}