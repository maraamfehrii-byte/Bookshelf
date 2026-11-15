package com.example.bookshelf.model

/**
 * Classes de données analysées par Gson à partir de la réponse de
 * https://www.googleapis.com/books/v1/volumes?q=...
 *
 * Les noms des propriétés correspondent aux clés JSON, donc aucune
 * annotation @SerializedName n'est nécessaire. Les champs optionnels
 * sont nullables, car l'API peut les omettre.
 */
data class BookResponse(
    val totalItems: Int = 0,
    val items: List<BookItem>? = null
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String? = null,
    val authors: List<String>? = null,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
)
