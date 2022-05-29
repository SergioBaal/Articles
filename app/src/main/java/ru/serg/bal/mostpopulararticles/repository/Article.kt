package ru.serg.bal.mostpopulararticles.repository

import com.google.gson.annotations.SerializedName


data class Article(
    val title: String = "",
    val photo: String = "",
    val description: String = "",
    val date: String = "",
    val url: String = ""
)