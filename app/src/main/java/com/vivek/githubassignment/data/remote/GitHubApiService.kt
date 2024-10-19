package com.vivek.githubassignment.data.remote

import com.vivek.githubassignment.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHubApiService {
    fun create(): GitHubApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GitHubApi::class.java)
    }
}