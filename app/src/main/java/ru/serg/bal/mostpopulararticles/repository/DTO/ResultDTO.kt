package ru.serg.bal.mostpopulararticles.repository.DTO


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultDTO(
    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("media")
    val media: List<MediaDTO>,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
) : Parcelable