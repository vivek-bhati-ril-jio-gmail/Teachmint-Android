import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vivek.githubassignment.data.model.Repo

@Composable
fun HomeScreen(
    viewModel: RepoViewModel = viewModel(),
    onRepoSelected: (Repo) -> Unit
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val repos by viewModel.repos.observeAsState(emptyList())
    val isLoading by viewModel.isLoadingRepoList.observeAsState(false)
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Search repositories...", color = Color.Gray) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = MaterialTheme.colors.primary,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.medium
            )
        }

        Button(
            onClick = {
                performSearch(viewModel, query.text, keyboardController)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text("Search", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(state = listState) {
            items(repos) { repo ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onRepoSelected(repo) },
                    elevation = 6.dp,
                    shape = MaterialTheme.shapes.medium,
                    backgroundColor = MaterialTheme.colors.surface
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${repos.indexOf(repo) + 1}. ${repo.name}",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onSurface)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "⭐ ${repo.stargazers_count}",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                }
            }

            // Load More Button
            item {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    if (repos.isNotEmpty()) {
                        Button(
                            onClick = { viewModel.loadMoreRepos(query.text) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                        ) {
                            Text("Load More", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}


private fun performSearch(viewModel: RepoViewModel, query: String, keyboardController: SoftwareKeyboardController?) {
    if (query.isNotBlank()) {
        viewModel.searchRepos(query)
        keyboardController?.hide()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen() {
    }
}