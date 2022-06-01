package ru.serg.bal.mostpopulararticles.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import ru.serg.bal.mostpopulararticles.domain.room.HistoryEntity
import ru.serg.bal.mostpopulararticles.repository.DTO.ArticleDTO
import ru.serg.bal.mostpopulararticles.repository.DTO.ResultDTO
import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO

const val TIMES_DOMAIN = "https://api.nytimes.com/"
const val TIMES_PATH = "svc/mostpopular/v2/viewed/1.json"
const val API_KEY = "api-key"
const val TIMES_API_KEY = "xFS9lbXmcO5Kl24Ix1cCmKT44Zkq3d8f"
const val KEY_BUNDLE_ARTICLE = "article"

fun convertSearchDtoToResultDTO(searchArticleDTO: SearchArticleDTO): List<ResultDTO> {
    return searchArticleDTO.results
}

fun convertResultDtoTOModel(resultDTO: List<ResultDTO>): List<ArticleDTO> {
    val article = mutableListOf<ArticleDTO>()
    var i = 0
    while (i < resultDTO.size) {
        if (resultDTO[i].media.isNotEmpty()) {
            val mediaData = resultDTO[i].media[0]
            article.add(
                ArticleDTO(
                    resultDTO[i].title,
                    mediaData.mediaMetadata[0].url,
                    mediaData.mediaMetadata[1].url,
                    resultDTO[i].abstract,
                    resultDTO[i].publishedDate,
                    resultDTO[i].url
                )
            )
            i++
        } else i++

    }
    return article.toList()
}

fun convertHistoryEntityToArticle(entityList: List<HistoryEntity>): List<ArticleDTO> {
    return entityList.map {
        ArticleDTO(it.title, it.photo, it.bigPhoto, it.description, it.date, it.url)
    }
}

fun convertArticleToEntity(article: ArticleDTO): HistoryEntity {
    return HistoryEntity(
        0,
        article.title,
        article.photo,
        article.bigPhoto,
        article.description,
        article.date,
        article.url
    )
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}

fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}


