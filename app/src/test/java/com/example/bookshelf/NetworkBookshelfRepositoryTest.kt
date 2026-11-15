package com.example.bookshelf

import com.example.bookshelf.data.NetworkBookshelfRepository
import com.example.bookshelf.fake.FakeBookshelfApiService
import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.model.BookResponse
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class NetworkBookshelfRepositoryTest {

    @Test
    fun getBooks_mapsResponseAndForcesHttps() = runTest {
        val repository = NetworkBookshelfRepository(FakeBookshelfApiService())

        val books = repository.getBooks("jazz")

        assertEquals(FakeDataSource.books, books)
    }

    @Test
    fun getBooks_whenItemsMissing_returnsEmptyList() = runTest {
        val repository = NetworkBookshelfRepository(
            FakeBookshelfApiService(BookResponse(totalItems = 0, items = null))
        )

        assertEquals(emptyList<Any>(), repository.getBooks("rien"))
    }

    @Test
    fun gson_parsesGoogleBooksJson() {
        val json = """
            {
              "totalItems": 1,
              "items": [
                {
                  "id": "abc",
                  "volumeInfo": {
                    "title": "Un titre",
                    "imageLinks": { "thumbnail": "http://example.com/a.jpg" }
                  }
                },
                { "id": "def", "volumeInfo": { "title": "Sans image" } }
              ]
            }
        """.trimIndent()

        val response = Gson().fromJson(json, BookResponse::class.java)

        assertEquals(1, response.totalItems)
        assertEquals("abc", response.items!![0].id)
        assertEquals("http://example.com/a.jpg", response.items!![0].volumeInfo.imageLinks?.thumbnail)
        assertNull(response.items!![1].volumeInfo.imageLinks)
    }
}
