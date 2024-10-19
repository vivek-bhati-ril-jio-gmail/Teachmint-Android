import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.vivek.githubassignment.data.model.Repo

@Composable
fun RepoDetailsScreen(repo: Repo?, navController: NavController, viewModel: RepoViewModel = viewModel()) {
    val isLoading by viewModel.isLoadingRepo.observeAsState(initial = true)

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                repo?.let {
                    // Make the card scrollable
                    LazyColumn {
                        item {
                            DisplayRepoDetails(it, navController)
                        }
                    }
                } ?: Text("Repository not found", modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
private fun DisplayRepoDetails(repo: Repo, navController: NavController) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = RoundedCornerShape(16.dp)
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val avatarUrl = repo.owner?.owner_avatar_url ?: "https://example.com/default_avatar.png"
            Image(
                painter = rememberAsyncImagePainter(avatarUrl),
                contentDescription = "Owner Avatar",
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(40.dp))
            )
            Text(text = repo.name, style = MaterialTheme.typography.h5.copy(fontSize = 24.sp, color = Color(0xFF1E88E5)), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = repo.description ?: "No description available", style = MaterialTheme.typography.body1.copy(color = Color.Gray), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))

            // Additional Repo Details
            Text(text = "Owner: ${repo.owner?.owner_login}", style = MaterialTheme.typography.body2.copy(color = Color(0xFF9E9E9E)))
            Text(text = "Created At: ${repo.created_at}", style = MaterialTheme.typography.body2.copy(color = Color(0xFF9E9E9E)))
            Text(text = "Language: ${repo.language ?: "N/A"}", style = MaterialTheme.typography.body2.copy(color = Color(0xFF9E9E9E)))
            Text(text = "License: ${repo.license?.license_name ?: "No License"}", style = MaterialTheme.typography.body2.copy(color = Color(0xFF9E9E9E)))

            // Display topics if available
            if (!repo.topics.isNullOrEmpty()) {
                Text(text = "Topics: ${repo.topics.joinToString(", ")}", style = MaterialTheme.typography.body2.copy(color = Color(0xFF9E9E9E)))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hyperlink for the project URL
            Text(
                text = "Project Link: View Project",
                style = MaterialTheme.typography.body2.copy(color = Color(0xFF1E88E5)),
                modifier = Modifier.clickable {
                    navController.navigate("web-view/${Uri.encode(repo.html_url)}")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Stars: ${repo.stargazers_count}", style = MaterialTheme.typography.body2.copy(color = Color(0xFF9E9E9E)))
            Text(text = "Forks: ${repo.forks_count}", style = MaterialTheme.typography.body2.copy(color = Color(0xFF9E9E9E)))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRepoDetailsScreen() {
    val mockNavController = rememberNavController() // Create a mock NavController
    RepoDetailsScreen(repo = Repo.nullObject, navController = mockNavController)
}
