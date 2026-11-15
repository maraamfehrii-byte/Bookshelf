# Bookshelf

Application Android (Kotlin + Jetpack Compose) qui affiche une grille de livres
issus de l'API Google Books.

## Concepts mis en pratique

- **Retrofit** : requête `GET https://www.googleapis.com/books/v1/volumes?q=...`
- **Gson** : conversion du JSON en data classes (`model/BookResponse.kt`)
- **Coroutines** : fonction `suspend` appelée depuis `viewModelScope`
- **Grille verticale** : `LazyVerticalGrid` + `AsyncImage` (Coil) pour les images asynchrones
- **Dépôt** : `BookshelfRepository` sépare l'UI de la couche de données
- **Injection de dépendances** : `AppContainer` ; les tests injectent des faux (`Fake*`)

## Ouvrir le projet

1. Android Studio (Koala ou plus récent) > **File > Open** > dossier `Bookshelf`.
2. Laissez la synchronisation Gradle se terminer (JDK 17).
3. Lancez l'application sur un émulateur ou un appareil (connexion Internet requise).

## Changer les livres affichés

Modifiez `DEFAULT_QUERY` dans `ui/BookshelfViewModel.kt` (par exemple `"cuisine"`, `"android"`...).

## Lancer les tests

```
./gradlew test
```

ou clic droit sur `app/src/test` > **Run Tests**.

## Structure

```
app/src/main/java/com/example/bookshelf
├── data     BookshelfApiService, BookshelfRepository, AppContainer
├── model    Book, BookResponse (+ BookItem, VolumeInfo, ImageLinks)
├── ui       BookshelfViewModel, BookshelfScreen
├── BookshelfApplication.kt
└── MainActivity.kt
app/src/test/java/com/example/bookshelf
├── fake     FakeBookshelfApiService, FakeBookshelfRepository, FakeDataSource
├── rules    MainDispatcherRule
├── NetworkBookshelfRepositoryTest.kt
└── BookshelfViewModelTest.kt
```
