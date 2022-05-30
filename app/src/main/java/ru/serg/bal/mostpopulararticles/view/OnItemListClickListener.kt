package ru.serg.bal.mostpopulararticles.view

import ru.serg.bal.mostpopulararticles.repository.Article

interface OnItemListClickListener {
    fun onItemClick(article: Article)
}