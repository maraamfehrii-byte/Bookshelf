package com.example.bookshelf.model

/**
 * Modèle utilisé par l'interface utilisateur.
 * Il est volontairement plus simple que la réponse JSON de l'API.
 */
data class Book(
    val id: String,
    val title: String,
    val thumbnailUrl: String?
)
