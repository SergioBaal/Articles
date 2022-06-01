package ru.serg.bal.mostpopulararticles.viewmodel

import ru.serg.bal.mostpopulararticles.repository.DTO.ArticleDTO

sealed class ArticleListState {
    object Loading : ArticleListState()
    data class Success(val article: List<ArticleDTO>) : ArticleListState()
    data class Error(val error: Throwable) : ArticleListState()
}