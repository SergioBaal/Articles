package ru.serg.bal.mostpopulararticles.viewmodel

import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.repository.DTO.ResultDTO
import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO

sealed class ArticleListState {
    object Loading : ArticleListState()
    data class Success(val article: List<Article>) : ArticleListState()
    data class Error(val error: Throwable) : ArticleListState()
}