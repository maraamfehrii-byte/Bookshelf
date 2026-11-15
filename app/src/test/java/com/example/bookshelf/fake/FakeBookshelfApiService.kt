package com.example.bookshelf.fake

import com.example.bookshelf.data.BookshelfApiService
import com.example.bookshelf.model.BookResponse

class FakeBookshelfApiService(
    private val response: BookResponse = FakeDataSource.response
) : BookshelfApiService {
    override suspend fun searchBooks(query: String): BookResponse = response
}
