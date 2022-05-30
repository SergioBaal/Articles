package ru.serg.bal.mostpopulararticles.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.serg.bal.mostpopulararticles.repository.DTO.ResultDTO
import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO
import ru.serg.bal.mostpopulararticles.utils.TIMES_API_KEY
import ru.serg.bal.mostpopulararticles.utils.TIMES_DOMAIN
import ru.serg.bal.mostpopulararticles.utils.TIMES_PATH

interface ArticleAPI {
    @GET("svc/mostpopular/v2/viewed/7.json")
    fun getArticles(
        @Query("api-key") apikey:String
    ):Call<SearchArticleDTO> //TODO: Надо к этому вернуться после проверки
}