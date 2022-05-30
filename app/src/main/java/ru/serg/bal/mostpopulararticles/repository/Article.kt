package ru.serg.bal.mostpopulararticles.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    var title: String = "",
    val photo: String = "",
    val description: String = "",
    val date: String = "",
    val url: String = ""
) : Parcelable