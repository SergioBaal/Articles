package ru.serg.bal.mostpopulararticles.view

import ru.serg.bal.mostpopulararticles.repository.DTO.ArticleDTO

interface OnItemListClickListener {
    fun onItemClick(article: ArticleDTO)
}