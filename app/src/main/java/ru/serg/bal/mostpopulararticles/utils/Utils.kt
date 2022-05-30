package ru.serg.bal.mostpopulararticles.utils

import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.repository.DTO.ResultDTO
import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO

class Utils {
}

const val TIMES_DOMAIN = "https://api.nytimes.com/svc/mostpopular/v2"
const val TIMES_PATH = "/viewed/1.json"
const val TIMES_API_KEY = "xFS9lbXmcO5Kl24Ix1cCmKT44Zkq3d8f"


fun convertSearchDtoToResultDTO(searchArticleDTO: SearchArticleDTO): List<ResultDTO> {
    return searchArticleDTO.results
}
fun convertResultDtoTOModel(resultDTO: List <ResultDTO>) : List<Article> {
    val article = mutableListOf<Article>()
    for (i in resultDTO.indices) {
        article.add(i, Article(resultDTO[i].title))
    }

return article.toList()

}