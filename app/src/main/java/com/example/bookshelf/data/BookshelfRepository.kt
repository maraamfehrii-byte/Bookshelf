package com.example.bookshelf.data

import com.example.bookshelf.model.Book

/**
 * Dépôt : seule source de données que connaît la couche UI.
 */
interface BookshelfRepository {
    suspend fun getBooks(query: String): List<Book>
}

/**
 * Implémentation qui récupère les données via le service réseau.
 */
class NetworkBookshelfRepository(
    private val apiService: BookshelfApiService
) : BookshelfRepository {

    override suspend fun getBooks(query: String): List<Book> =
        apiService.searchBooks(query)
            .items
            .orEmpty()
            .map { item ->
                Book(
                    id = item.id,
                    title = item.volumeInfo.title ?: "",
                    // Les miniatures sont en http:// ; Coil/Android exigent https://
                    thumbnailUrl = item.volumeInfo.imageLinks?.thumbnail
                        ?.replace("http://", "https://")
                )
            }
            .distinctBy { it.id }
}
