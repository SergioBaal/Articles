package ru.serg.bal.mostpopulararticles.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.serg.bal.mostpopulararticles.databinding.FragmentArticlesListRecyclerItemBinding
import ru.serg.bal.mostpopulararticles.repository.DTO.ArticleDTO

class ArticleListAdapter(
    private val onItemListClickListener: OnItemListClickListener,
    private var articleList: ArrayList<ArticleDTO> = ArrayList<ArticleDTO>()
) : RecyclerView.Adapter<ArticleListAdapter.ArticleHolder>() {

    fun setData(newArticleList: List<ArticleDTO>) {
        val diffUtilCallBack = DiffUtilCallBack(articleList, newArticleList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        articleList.clear()
        articleList.addAll(newArticleList)
        diffResult.dispatchUpdatesTo(this)
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
        val article = articleList[position]
        holder.bind(article)
    }

    override fun getItemCount() = articleList.size

    inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: ArticleDTO) {
            FragmentArticlesListRecyclerItemBinding.bind(itemView).apply {
                titleListTextView.text = article.title
                photoListImageView.load(article.photo)
                with(ArticleDetailsFragment) { photoListImageView.load(article.bigPhoto) }
                root.setOnClickListener {
                    onItemListClickListener.onItemClick(article)
                }
            }
        }
    }
}

class DiffUtilCallBack(
    private val oldArticleList: List<ArticleDTO>,
    private val newArticleList: List<ArticleDTO>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldArticleList.size
    }

    override fun getNewListSize(): Int {
        return newArticleList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArticleList[oldItemPosition] == newArticleList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArticleList[oldItemPosition] == newArticleList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}