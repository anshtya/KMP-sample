package com.anshtya.kmpsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshtya.kmpsample.model.Post
import com.anshtya.kmpsample.repository.PostRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel(
    private val postRepository: PostRepository
): ViewModel() {
    private var initJob: Job? = null

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    val uiState: StateFlow<PostsUiState> = postRepository.posts
        .catch { throwable -> _errorMessage.update { throwable.message } }
        .map { PostsUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = PostsUiState.Loading
        )

    init { initializeData() }

    fun onErrorShown() {
        _errorMessage.update { null }
    }

    private fun initializeData() {
        if (initJob != null) return

        initJob = viewModelScope.launch {
            try {
                postRepository.fetchAndStorePosts().getOrThrow()
            } catch (e: Exception) {
                println(e.message)
                _errorMessage.update { "Failed to fetch posts" }
            }
            initJob = null
        }
    }
}

sealed interface PostsUiState {
    data object Loading: PostsUiState
    data class Success(val posts: List<Post>): PostsUiState
}