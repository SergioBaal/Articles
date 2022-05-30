package ru.serg.bal.mostpopulararticles.viewmodel

import ru.serg.bal.mostpopulararticles.repository.Article

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val article: Article) : DetailsState()
    data class Error(val error: Throwable) : DetailsState()
}