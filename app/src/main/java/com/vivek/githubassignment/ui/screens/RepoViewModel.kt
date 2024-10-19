import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vivek.githubassignment.data.dao.RepoDao
import com.vivek.githubassignment.data.model.Repo
import com.vivek.githubassignment.data.repository.RepoRepository
import com.vivek.githubassignment.database.AppDatabase
import kotlinx.coroutines.launch

class RepoViewModel(private val repository: RepoRepository) : ViewModel() {
    private val _repos = MutableLiveData<List<Repo>>(emptyList()) // Initialize with an empty list
    val repos: LiveData<List<Repo>> get() = _repos

    private val _repo = MutableLiveData<Repo>()
    private val _isLoadingRepoList = MutableLiveData<Boolean>(false)
    val isLoadingRepoList: LiveData<Boolean> get() = _isLoadingRepoList

    private val _isLoadingRepo = MutableLiveData<Boolean>(false)
    val isLoadingRepo: LiveData<Boolean> get() = _isLoadingRepo

    private var currentPage = 1
    private val pageSize = 10

    private suspend fun fetchRepos(query: String, currentPage: Int, type: Int) {
        viewModelScope.launch {
            try {
                _isLoadingRepoList.value = true // Start loading
                val result = repository.searchRepos(query, currentPage, pageSize)
                if (type == 0) {
                    // If it's the first page, replace the list
                    _repos.value = result
//                    repoDao.insertAll(result.take(pageSize))
                } else {
                    // Otherwise, append new results
                    _repos.value = _repos.value?.plus(result)
//                    repoDao.insertAll(result.take(15))
                }
            } catch (e: Exception) {
            } finally {
                _isLoadingRepoList.value = false // Stop loading
            }
        }
    }

    fun loadMoreRepos(query: String) {
        viewModelScope.launch {
            currentPage += 1
            fetchRepos(query, currentPage, 1)
        }
    }

    fun searchRepos(query: String) {
        viewModelScope.launch {
            currentPage = 1
            fetchRepos(query, currentPage, 0)
        }
    }
}
