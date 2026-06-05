package ec.edu.puce.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.ui.screens.RepoEditForm
import ec.edu.puce.githubclient.ui.screens.RepoForm
import ec.edu.puce.githubclient.ui.screens.RepoList
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme
import ec.edu.puce.githubclient.viewmodels.RepoEditViewModel
import ec.edu.puce.githubclient.viewmodels.RepoFormViewModel
import ec.edu.puce.githubclient.viewmodels.RepoListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubClientTheme {
                var currentScreen by remember { mutableStateOf("repoList") }
                var selectedRepo by remember { mutableStateOf<Repository?>(null) }

                val listViewModel: RepoListViewModel = viewModel()
                val formViewModel: RepoFormViewModel = viewModel()
                val editViewModel: RepoEditViewModel = viewModel()

                when (currentScreen) {
                    "repoList" -> RepoList(
                        onNavigateToForm = { currentScreen = "repoForm" },
                        onNavigateToEdit = { repo ->
                            selectedRepo = repo
                            currentScreen = "repoEdit"
                        }
                    )
                    "repoForm" -> RepoForm(
                        onBackClick = {
                            formViewModel.resetError()
                            currentScreen = "repoList"
                        },
                        onSaveSuccess = {
                            listViewModel.fetchRepos()
                            currentScreen = "repoList"
                        }
                    )
                    "repoEdit" -> selectedRepo?.let { repo ->
                        RepoEditForm(
                            owner = repo.owner.login,
                            repoName = repo.name,
                            repoDescription = repo.description,
                            onBackClick = {
                                editViewModel.resetError()
                                currentScreen = "repoList"
                            },
                            onSaveSuccess = {
                                listViewModel.fetchRepos()
                                currentScreen = "repoList"
                            },
                            onDeleteSuccess = {
                                listViewModel.fetchRepos()
                                currentScreen = "repoList"
                            },
                            viewModel = editViewModel
                        )
                    }
                }
            }
        }
    }
}