package ru.serg.bal.mostpopulararticles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.serg.bal.mostpopulararticles.repository.*

class ArticleViewModel(
    private val liveData: MutableLiveData<ArticleListState> = MutableLiveData(),
    private val liveDetailsData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val repositoryAdd: ArticleRepositoryAdd = ArticleRepositoryRoomImpl()
) : ViewModel() {

    private var repository: ArticleRepository = ArticleListRepositoryImpl()

    fun getLiveData(): LiveData<ArticleListState> {
        return liveData
    }

    fun getDetailsLiveData(): LiveData<DetailsState> {
        return liveDetailsData
    }


    fun getArticle() {
        liveData.postValue(ArticleListState.Loading)
        repository = if (isInternet()) {
            ArticleListRepositoryImpl()
        } else {
            ArticleRepositoryRoomImpl()
        }
        repository.getArticleFromRepository(object : Callback {
            override fun onResponse(articleList: List<Article>) {
                liveData.postValue(ArticleListState.Success(articleList))
                if (isInternet()) {
                    for (i in articleList.indices)
                        repositoryAdd.addArticle(articleList[i])
                }
            }

            override fun onFail() {
                liveData.postValue(ArticleListState.Error(Throwable()))
                Log.d("@@@", "ошибка во ViewModel")
            }
        })

    }

    fun getArticleDetails(article: Article) {
        liveDetailsData.postValue(DetailsState.Success(article))
    }


    fun isInternet(): Boolean {
        return true  //TODO: заглушка
    }


    interface Callback {
        fun onResponse(article: List<Article>)
        fun onFail()
    }
}
