package ru.serg.bal.mostpopulararticles.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class MyArticleDB : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}