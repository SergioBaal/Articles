package ru.serg.bal.mostpopulararticles.repository.DTO


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

@Parcelize
data class ResultDTO(
    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("media")
    val media: List<MediaDTO>,
    @SerializedName("nytdsection")
    val publishedDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
): Parcelable