package ru.serg.bal.mostpopulararticles.repository

import ru.serg.bal.mostpopulararticles.repository.DTO.ArticleDTO

interface ArticleRepositoryAdd {
    fun addArticle(article: ArticleDTO)
}