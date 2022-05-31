package ru.serg.bal.mostpopulararticles.utils

import ru.serg.bal.mostpopulararticles.domain.room.HistoryEntity
import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.repository.DTO.ResultDTO
import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO

class Utils {
}

const val TIMES_DOMAIN = "https://api.nytimes.com/"
const val TIMES_PATH = "svc/mostpopular/v2/viewed/1.json"
const val API_KEY = "api-key"
const val TIMES_API_KEY = "xFS9lbXmcO5Kl24Ix1cCmKT44Zkq3d8f"
const val KEY_BUNDLE_ARTICLE = "article"
const val KEY_SP_IS_INTERNET = "internet"


fun convertSearchDtoToResultDTO(searchArticleDTO: SearchArticleDTO): List<ResultDTO> {
    return searchArticleDTO.results
}

fun convertResultDtoTOModel(resultDTO: List<ResultDTO>): List<Article> {
    val article = mutableListOf<Article>()
    var i = 0
    while (i < resultDTO.size) {
        if (resultDTO[i].media.isNotEmpty()) {
            val mediaData = resultDTO[i].media[0]
            article.add(
                Article(
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

fun convertHistoryEntityToArticle(entityList: List<HistoryEntity>): List<Article> {
    return entityList.map {
        Article(it.title, it.photo, it.bigPhoto, it.description, it.date, it.url)
    }
}

fun convertArticleToEntity(article: Article): HistoryEntity {
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


