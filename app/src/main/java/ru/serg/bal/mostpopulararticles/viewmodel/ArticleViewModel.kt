package ru.serg.bal.mostpopulararticles.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.serg.bal.mostpopulararticles.MyApp
import ru.serg.bal.mostpopulararticles.repository.ArticleLocalRepository
import ru.serg.bal.mostpopulararticles.repository.ArticleRemoteRepository
import ru.serg.bal.mostpopulararticles.repository.ArticleRepository
import ru.serg.bal.mostpopulararticles.repository.ArticleRepositoryAdd
import ru.serg.bal.mostpopulararticles.repository.DTO.ArticleDTO
import ru.serg.bal.mostpopulararticles.utils.isOnline

class ArticleViewModel(
    private val liveData: MutableLiveData<ArticleListState> = MutableLiveData(),
    private val liveDetailsData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val repositoryAdd: ArticleRepositoryAdd = ArticleLocalRepository(),
) : ViewModel() {
    private var repository: ArticleRepository = ArticleRemoteRepository()
    fun getLiveData(): LiveData<ArticleListState> {
        return liveData
    }

    fun getDetailsLiveData(): LiveData<DetailsState> {
        return liveDetailsData
    }

    fun getArticle() {
        liveData.postValue(ArticleListState.Loading)
        repository = if (isOnline(MyApp.appContext as Context)) {
            ArticleRemoteRepository()
        } else {
            ArticleLocalRepository()
        }
        repository.getArticleFromRepository(object : Callback {
            override fun onResponse(article: List<ArticleDTO>) {
                liveData.postValue(ArticleListState.Success(article))
                if (isOnline(MyApp.appContext as Context)) {
                    for (i in article.indices)
                        repositoryAdd.addArticle(article[i])
                }
            }

            override fun onFail() {
                liveData.postValue(ArticleListState.Error(Throwable()))
            }
        })
    }

    fun getArticleDetails(article: ArticleDTO) {
        liveDetailsData.postValue(DetailsState.Success(article))
    }

    interface Callback {
        fun onResponse(article: List<ArticleDTO>)
        fun onFail()
    }
}
