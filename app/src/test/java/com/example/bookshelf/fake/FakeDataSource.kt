package com.example.bookshelf.fake

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BookResponse
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

object FakeDataSource {

    val response = BookResponse(
        totalItems = 2,
        items = listOf(
            BookItem(
                id = "1",
                volumeInfo = VolumeInfo(
                    title = "Jazz",
                    authors = listOf("Auteur A"),
                    imageLinks = ImageLinks(thumbnail = "http://example.com/1.jpg")
                )
            ),
            BookItem(
                id = "2",
                volumeInfo = VolumeInfo(title = "Blues")
            )
        )
    )

    /** Résultat attendu après transformation par le dépôt. */
    val books = listOf(
        Book(id = "1", title = "Jazz", thumbnailUrl = "https://example.com/1.jpg"),
        Book(id = "2", title = "Blues", thumbnailUrl = null)
    )
}
