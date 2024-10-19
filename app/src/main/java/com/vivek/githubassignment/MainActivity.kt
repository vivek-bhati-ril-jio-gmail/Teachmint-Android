package com.vivek.githubassignment

import HomeScreen
import RepoDetailsScreen
import RepoViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vivek.githubassignment.data.model.Repo
import com.vivek.githubassignment.data.remote.GitHubApiService
import com.vivek.githubassignment.data.repository.RepoRepository
import com.vivek.githubassignment.data.repository.RepoViewModelFactory
import com.vivek.githubassignment.ui.screens.WebViewScreen

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create an instance of GitHubApi
        val api = GitHubApiService.create()
        val repository = RepoRepository(api)
        val factory = RepoViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[RepoViewModel::class.java]

        // State to hold the selected Repo
        var selectedRepo by mutableStateOf<Repo?>(null)

        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel) { repo ->
                                selectedRepo = repo // Update selected repo
                                navController.navigate("repoDetails")
                            }
                        }
                        composable("repoDetails") {
                            RepoDetailsScreen(selectedRepo, navController, viewModel) // Pass the selected repo
                        }
                        composable("web-view/{url}") { backStackEntry ->
                            val url = backStackEntry.arguments?.getString("url") ?: ""
                            WebViewScreen(url)
                        }
                    }
                }
            }
        }
    }
}