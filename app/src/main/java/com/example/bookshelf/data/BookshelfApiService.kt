package com.example.bookshelf.data

import com.example.bookshelf.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface Retrofit pour l'API Google Books.
 * L'URL de base (https://www.googleapis.com/books/v1/) est définie dans [AppContainer].
 */
interface BookshelfApiService {

    @GET("volumes?maxResults=40")
    suspend fun searchBooks(@Query("q") query: String): BookResponse
}
