package ru.serg.bal.mostpopulararticles.domain.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String = "",
    val photo: String = "",
    val bigPhoto: String = "",
    val description: String = "",
    val date: String = "",
    val url: String = ""
)
