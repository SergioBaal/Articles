package ru.serg.bal.mostpopulararticles.repository

import ru.serg.bal.mostpopulararticles.viewmodel.ArticleViewModel

interface ArticleRepository {
    fun getArticleFromRepository(callback: ArticleViewModel.Callback)
}