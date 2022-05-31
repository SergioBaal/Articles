package ru.serg.bal.mostpopulararticles.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_articles_list.*
import ru.serg.bal.mostpopulararticles.R
import ru.serg.bal.mostpopulararticles.databinding.FragmentArticlesListBinding
import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.utils.KEY_BUNDLE_ARTICLE
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleListState
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleViewModel

class ArticleListFragment : Fragment(), OnItemListClickListener {
    private var _binding: FragmentArticlesListBinding? = null
    private val binding: FragmentArticlesListBinding
        get() = _binding!!


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(this).get(ArticleViewModel::class.java)
    }

    private val adapter = ArticleListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        binding.recyclerView.adapter = adapter
        val observer = object : Observer<ArticleListState> {
            override fun onChanged(data: ArticleListState) {
                renderData(data)
            }
        }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getArticle()


    }


    private fun renderData(state: ArticleListState) {
        when (state) {
            is ArticleListState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.mainListFragment.showSnackBar(
                    "Ошибка соединения!",
                    "Повторить?",
                    {
                        val data = arguments?.getParcelable<Article>(KEY_BUNDLE_ARTICLE)
                        if (data != null) {
                            viewModel.getArticleDetails(data)
                        }
                    }

                )
            }
            is ArticleListState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is ArticleListState.Success -> {
                loadingLayout.visibility = View.GONE
                adapter.setData(state.article)
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ArticleListFragment()
    }

    override fun onItemClick(article: Article) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_BUNDLE_ARTICLE, article)
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.container,
            ArticleDetailsFragment.newInstance(Bundle().apply {
                putParcelable(KEY_BUNDLE_ARTICLE, article)
            })
        ).addToBackStack("").commit()
    }

}