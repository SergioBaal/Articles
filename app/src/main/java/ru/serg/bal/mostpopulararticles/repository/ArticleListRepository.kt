package ru.serg.bal.mostpopulararticles.repository

import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleListViewModel

interface ArticleListRepository {
    fun getArticleFromServer(callback : ArticleListViewModel.Callback)
}