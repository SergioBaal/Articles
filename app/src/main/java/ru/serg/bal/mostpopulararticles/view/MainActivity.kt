package ru.serg.bal.mostpopulararticles.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.serg.bal.mostpopulararticles.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ArticleListFragment.newInstance()).addToBackStack("")
                .commit()
        }
    }
}