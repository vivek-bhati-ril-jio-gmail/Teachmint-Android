package com.vivek.githubassignment.data.model

data class RepoListResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repo>
)