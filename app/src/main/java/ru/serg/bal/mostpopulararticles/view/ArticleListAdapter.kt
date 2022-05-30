package ru.serg.bal.mostpopulararticles.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.serg.bal.mostpopulararticles.databinding.FragmentArticlesListRecyclerItemBinding
import ru.serg.bal.mostpopulararticles.repository.Article
import ru.serg.bal.mostpopulararticles.repository.DTO.ResultDTO
import ru.serg.bal.mostpopulararticles.repository.DTO.SearchArticleDTO

class ArticleListAdapter (
    private val onItemListClickListener: OnItemListClickListener,
    private var data: List<Article> = listOf()
) : RecyclerView.Adapter<ArticleListAdapter.ArticleHolder>() {


    fun setData(dataNew: List<Article>) {
        this.data = dataNew
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val binding = FragmentArticlesListRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount() = data.size


    inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            FragmentArticlesListRecyclerItemBinding.bind(itemView).apply {
                titleListTextView.text = article.title
                root.setOnClickListener {
                    onItemListClickListener.onItemClick(article)
                }
            }
        }
    }
}