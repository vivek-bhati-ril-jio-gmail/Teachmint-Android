package com.vivek.githubassignment.data.repository

import RepoViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RepoViewModelFactory(private val repository: RepoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            return RepoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}