package com.example.bookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val books: List<Book>) : BookshelfUiState
    data object Error : BookshelfUiState
    data object Loading : BookshelfUiState
}

class BookshelfViewModel(
    private val repository: BookshelfRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookshelfUiState>(BookshelfUiState.Loading)
    val uiState: StateFlow<BookshelfUiState> = _uiState.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks(query: String = DEFAULT_QUERY) {
        viewModelScope.launch {
            _uiState.value = BookshelfUiState.Loading
            _uiState.value = try {
                BookshelfUiState.Success(repository.getBooks(query))
            } catch (e: IOException) {
                BookshelfUiState.Error
            } catch (e: HttpException) {
                BookshelfUiState.Error
            }
        }
    }

    companion object {
        /** Thème des livres affichés : changez-le pour afficher d'autres livres. */
        const val DEFAULT_QUERY = "jazz history"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as BookshelfApplication
                BookshelfViewModel(application.container.bookshelfRepository)
            }
        }
    }
}
