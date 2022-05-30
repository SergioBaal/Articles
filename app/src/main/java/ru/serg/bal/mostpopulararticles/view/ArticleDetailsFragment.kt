package ru.serg.bal.mostpopulararticles.view

import android.os.Bundle
import androidx.fragment.app.Fragment

class ArticleDetailsFragment: Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): ArticleDetailsFragment {
            val fragment = ArticleDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}