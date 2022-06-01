package ru.serg.bal.mostpopulararticles.viewmodel

import ru.serg.bal.mostpopulararticles.repository.DTO.ArticleDTO

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val article: ArticleDTO) : DetailsState()
    data class Error(val error: Throwable) : DetailsState()
}