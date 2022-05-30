package ru.serg.bal.mostpopulararticles.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_articles_list.*
import kotlinx.android.synthetic.main.fragment_articles_list_recycler_item.*
import ru.serg.bal.mostpopulararticles.R
import ru.serg.bal.mostpopulararticles.databinding.FragmentArticlesListBinding
import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleListState
import ru.serg.bal.mostpopulararticles.viewmodel.ArticleListViewModel

class ArticleListFragment : Fragment(), OnItemListClickListener {
    private var _binding: FragmentArticlesListBinding? = null
    private val binding: FragmentArticlesListBinding
        get() = _binding!!


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val viewModel: ArticleListViewModel by lazy {
        ViewModelProvider(this).get(ArticleListViewModel::class.java)
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





    private fun renderData (state: ArticleListState) {
        when (state) {
            is ArticleListState.Error -> { }
            is ArticleListState.Loading -> {}
            is ArticleListState.Success -> {
                loadingLayout.visibility = View.GONE
                    adapter.setData(state.article)
                viewModel.getArticle()


                }
            }
        }



    companion object {
        @JvmStatic
        fun newInstance() = ArticleListFragment()
    }

    override fun onItemClick(article: Article) {
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.container,
            ArticleDetailsFragment.newInstance(Bundle().apply {

            })
        ).addToBackStack("").commit()
    }

}