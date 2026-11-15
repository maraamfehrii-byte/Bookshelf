package com.example.bookshelf

import com.example.bookshelf.fake.FakeBookshelfRepository
import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.rules.MainDispatcherRule
import com.example.bookshelf.ui.BookshelfUiState
import com.example.bookshelf.ui.BookshelfViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun viewModel_loadsBooks_emitsSuccess() {
        val viewModel = BookshelfViewModel(FakeBookshelfRepository())

        assertEquals(
            BookshelfUiState.Success(FakeDataSource.books),
            viewModel.uiState.value
        )
    }

    @Test
    fun viewModel_networkFailure_emitsError() {
        val viewModel = BookshelfViewModel(FakeBookshelfRepository(shouldFail = true))

        assertEquals(BookshelfUiState.Error, viewModel.uiState.value)
    }
}
