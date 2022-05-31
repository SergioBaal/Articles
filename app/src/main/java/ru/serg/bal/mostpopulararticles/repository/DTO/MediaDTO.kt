package ru.serg.bal.mostpopulararticles.repository.DTO


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaDTO(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadataDTO>,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("type")
    val type: String
) : Parcelable