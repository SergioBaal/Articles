package ru.serg.bal.mostpopulararticles.view

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import kotlinx.android.synthetic.main.fragments_article_details.*
import ru.serg.bal.mostpopulararticles.databinding.FragmentsArticleDetailsBinding
import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleListViewModel
import ru.serg.bal.mostpopulararticles.viewmodel.DetailsState


class ArticleDetailsFragment: Fragment() {


    private var _binding: FragmentsArticleDetailsBinding? = null
    private val binding: FragmentsArticleDetailsBinding
        get() = _binding!!


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentsArticleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: ArticleListViewModel by lazy {
        ViewModelProvider(this).get(ArticleListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetailsLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
       val data = arguments?.getParcelable<Article>("123")
            viewModel.getArticleDetails(data!!)

    }

    private fun renderData (state: DetailsState) {
        when (state) {
            is DetailsState.Error -> { }
            is DetailsState.Loading -> {}
            is DetailsState.Success -> {
                val article = arguments?.getParcelable<Article>("123")
                titleDetailsTextView.text = article!!.title
                photoDetails.load(article.photo)
                descriptionDetailsTextView.text = article.description
                dateDetailsTextView.text = article.date.toString()

                urlDetailsTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                urlDetailsTextView.setOnClickListener {
                    val url = "${article.url}"
                        val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url);
                        startActivity(intent);
                    }

            }
        }
    }




    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): ArticleDetailsFragment {
            val fragment = ArticleDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}