package ru.serg.bal.mostpopulararticles.repository.DTO


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class MediaMetadataDTO(
    @SerializedName("format")
    val format: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
): Parcelable