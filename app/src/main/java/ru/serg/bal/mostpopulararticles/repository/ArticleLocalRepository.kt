package ru.serg.bal.mostpopulararticles.repository

import android.util.Log
import ru.serg.bal.mostpopulararticles.BuildConfig
import ru.serg.bal.mostpopulararticles.MyApp
import ru.serg.bal.mostpopulararticles.repository.DTO.ArticleDTO
import ru.serg.bal.mostpopulararticles.utils.convertArticleToEntity
import ru.serg.bal.mostpopulararticles.utils.convertHistoryEntityToArticle
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleViewModel

class ArticleLocalRepository : ArticleRepository, ArticleRepositoryAdd {
    override fun getArticleFromRepository(callback: ArticleViewModel.Callback) {
        val list = convertHistoryEntityToArticle(MyApp.getHistoryDao().getAll())
        if (list.isEmpty()) {
            callback.onFail()
            if (BuildConfig.DEBUG) {
                Log.d("@@@", "Ошибка в ответе от Room")
            }

        } else {
            callback.onResponse(list)
        }
    }

    override fun addArticle(article: ArticleDTO) {
        MyApp.getHistoryDao().insert(convertArticleToEntity(article))
    }
}