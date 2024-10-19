package com.vivek.githubassignment.data.remote

import com.vivek.githubassignment.data.model.Repo
import com.vivek.githubassignment.data.model.RepoListResponse
import com.vivek.githubassignment.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
        @Header("Authorization") authorization: String = Constants.AUTHORIZATION_TOKEN,
        @Header("Accept") accept: String = Constants.ACCEPT_HEADER,
        @Header("X-GitHub-Api-Version") apiVersion: String = Constants.GITHUB_API_VERSION
    ): Response<RepoListResponse>
}