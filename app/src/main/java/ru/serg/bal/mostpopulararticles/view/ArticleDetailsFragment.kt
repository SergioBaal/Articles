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
import com.google.android.material.snackbar.Snackbar
import ru.serg.bal.mostpopulararticles.databinding.FragmentsArticleDetailsBinding
import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.utils.KEY_BUNDLE_ARTICLE
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleViewModel
import ru.serg.bal.mostpopulararticles.viewmodel.DetailsState


class ArticleDetailsFragment : Fragment() {


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

    private val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(this).get(ArticleViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailsLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        val data = arguments?.getParcelable<Article>(KEY_BUNDLE_ARTICLE)
        if (data != null) {
            viewModel.getArticleDetails(data)
        }


    }

    private fun renderData(state: DetailsState) {
        when (state) {
            is DetailsState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.fragmentDetails.showSnackBar(
                    "Ошибка соединения!",
                    "Повторить?",
                    {val data = arguments?.getParcelable<Article>(KEY_BUNDLE_ARTICLE)
                        if (data != null) {
                            viewModel.getArticle()}
                        }

                )
            }
            is DetailsState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is DetailsState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val article = arguments?.getParcelable<Article>(KEY_BUNDLE_ARTICLE)

                with(binding) {
                        titleDetailsTextView.text = article!!.title
                        photoDetails.load(article.bigPhoto)
                        descriptionDetailsTextView.text = article.description
                        dateDetailsTextView.text = article.date
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

fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}