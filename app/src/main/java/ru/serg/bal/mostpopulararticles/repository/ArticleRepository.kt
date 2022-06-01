package ru.serg.bal.mostpopulararticles.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO
import ru.serg.bal.mostpopulararticles.utils.API_KEY
import ru.serg.bal.mostpopulararticles.utils.TIMES_PATH
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleViewModel

interface ArticleRepository {
    fun getArticleFromRepository(callback: ArticleViewModel.Callback)
}

interface ArticleAPI {
    @GET(TIMES_PATH)
    fun getArticles(
        @Query(API_KEY) apikey: String
    ): Call<SearchArticleDTO>
}