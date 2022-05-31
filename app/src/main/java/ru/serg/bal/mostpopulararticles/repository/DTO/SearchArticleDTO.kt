package ru.serg.bal.mostpopulararticles.repository.DTO


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SearchArticleDTO(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<ResultDTO>,
    @SerializedName("status")
    val status: String
) : Parcelable