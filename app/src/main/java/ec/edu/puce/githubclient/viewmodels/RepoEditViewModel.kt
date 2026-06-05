package ec.edu.puce.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.edu.puce.githubclient.models.UpdateRepositoryPayload
import ec.edu.puce.githubclient.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoEditViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isSuccess = MutableStateFlow(value = false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    private val _isDeleted = MutableStateFlow(value = false)
    val isDeleted: StateFlow<Boolean> = _isDeleted.asStateFlow()

    private val _errorMsg = MutableStateFlow<String?>(value = null)
    val errMsg: StateFlow<String?> = _errorMsg.asStateFlow()

    fun updateRepository(owner: String, repoName: String, newName: String, newDescription: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                val payload = UpdateRepositoryPayload(name = newName, description = newDescription)
                RetrofitClient.apiService.updateRepository(owner, repoName, payload)
                _isSuccess.value = true
            } catch (e: Exception) {
                _isSuccess.value = false
                _errorMsg.value = "Error al actualizar repositorio: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteRepository(owner: String, repoName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                val response = RetrofitClient.apiService.deleteRepository(owner, repoName)
                if (response.code() == 204) {
                    _isDeleted.value = true
                } else {
                    _errorMsg.value = "Error al eliminar repositorio: código ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMsg.value = "Error al eliminar repositorio: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetSuccess() {
        _isSuccess.value = false
    }

    fun resetDeleted() {
        _isDeleted.value = false
    }

    fun resetError() {
        _errorMsg.value = null
    }
}