package ru.serg.bal.mostpopulararticles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.repository.ArticleListRepository
import ru.serg.bal.mostpopulararticles.repository.ArticleListRepositoryImpl

class ArticleListViewModel(
    private val liveData: MutableLiveData<ArticleListState> = MutableLiveData(),
    private val liveDetailsData: MutableLiveData<DetailsState> = MutableLiveData()

    ):ViewModel(){

    private val repositoryList:ArticleListRepository = ArticleListRepositoryImpl()

    fun getLiveData() : LiveData <ArticleListState> {
        return liveData
    }

    fun getDetailsLiveData() : LiveData <DetailsState> {
        return liveDetailsData
    }



    fun getArticle() {
            liveData.postValue(ArticleListState.Loading)
            repositoryList.getArticleFromServer(object : Callback {
                override fun onResponse(article: List<Article>) {
                    liveData.postValue(ArticleListState.Success(article))
                }

                override fun onFail() {
                    liveData.postValue(ArticleListState.Error(Throwable()))
                    Log.d("@@@", " ошибка в detailsViewModel 1")
                }
            })

    }
    fun getArticleDetails(article: Article) {

                liveDetailsData.postValue(DetailsState.Success(article))
            }







    interface Callback {
        fun onResponse(article : List<Article>)
        fun onFail()
    }
}
