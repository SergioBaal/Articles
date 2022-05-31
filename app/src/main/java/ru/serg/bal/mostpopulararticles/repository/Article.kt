package ru.serg.bal.mostpopulararticles.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val title: String = "",
    val photo: String = "",
    val bigPhoto: String = "",
    val description: String = "",
    val date: String = "",
    val url: String = ""
) : Parcelable