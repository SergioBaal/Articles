package ru.serg.bal.mostpopulararticles

import android.app.Application
import androidx.room.Room
import ru.serg.bal.mostpopulararticles.domain.room.HistoryDao
import ru.serg.bal.mostpopulararticles.domain.room.MyArticleDB

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        private var db: MyArticleDB? = null
        var appContext: MyApp? = null
        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                if (appContext != null) {
                    db = Room.databaseBuilder(appContext!!, MyArticleDB::class.java, "room")
                        .allowMainThreadQueries().build()
                } else {
                    throw IllegalStateException("Error")
                }
            }
            return db!!.historyDao()
        }
    }
}