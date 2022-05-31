package ru.serg.bal.mostpopulararticles.repository

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO
import ru.serg.bal.mostpopulararticles.utils.TIMES_API_KEY
import ru.serg.bal.mostpopulararticles.utils.TIMES_DOMAIN
import ru.serg.bal.mostpopulararticles.utils.convertResultDtoTOModel
import ru.serg.bal.mostpopulararticles.utils.convertSearchDtoToResultDTO
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleViewModel


val timesAPI = Retrofit.Builder().apply {
    baseUrl(TIMES_DOMAIN)
    addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
}.build().create(ArticleAPI::class.java)

class ArticleListRepositoryImpl : ArticleRepository {
    override fun getArticleFromRepository(callback: ArticleViewModel.Callback) {
        timesAPI.getArticles(TIMES_API_KEY)
            .enqueue(object : Callback<SearchArticleDTO> {
                override fun onResponse(
                    call: Call<SearchArticleDTO>,
                    response: Response<SearchArticleDTO>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val article = convertResultDtoTOModel(convertSearchDtoToResultDTO(it))
                            callback.onResponse(article)
                        }
                    } else {
                        callback.onFail()
                        Log.d("@@@", "Ошибка в ответе с сервера ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<SearchArticleDTO>, t: Throwable) {
                    callback.onFail()
                    Log.d("@@@", "Ошибка в RepositoryImp ${t.message}")
                }
            })
    }
}