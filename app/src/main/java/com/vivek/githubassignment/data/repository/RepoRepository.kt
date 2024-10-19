package com.vivek.githubassignment.data.repository

import com.vivek.githubassignment.data.model.Repo
import com.vivek.githubassignment.data.remote.GitHubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RepoRepository(private val api: GitHubApi) {
    suspend fun searchRepos(query: String, page: Int, pageSize: Int): List<Repo> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.searchRepos(query, page, pageSize)
                if (response.isSuccessful) {
                    response.body()!!.items
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}