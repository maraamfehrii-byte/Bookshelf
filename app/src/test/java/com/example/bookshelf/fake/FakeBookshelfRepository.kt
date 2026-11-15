package com.example.bookshelf.fake

import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.Book
import java.io.IOException

class FakeBookshelfRepository(
    private val books: List<Book> = FakeDataSource.books,
    private val shouldFail: Boolean = false
) : BookshelfRepository {
    override suspend fun getBooks(query: String): List<Book> {
        if (shouldFail) throw IOException("Erreur réseau simulée")
        return books
    }
}
