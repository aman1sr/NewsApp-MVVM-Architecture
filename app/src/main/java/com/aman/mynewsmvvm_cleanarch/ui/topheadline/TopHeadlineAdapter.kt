package com.aman.mynewsmvvm_cleanarch.ui.topheadline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aman.mynewsmvvm_cleanarch.data.local.entity.Article
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import com.aman.mynewsmvvm_cleanarch.databinding.TopHeadlineItemLayoutBinding
import com.aman.mynewsmvvm_cleanarch.utils.ItemClickListener
import com.bumptech.glide.Glide

class TopHeadlineAdapter(
    private val articleList: ArrayList<Any>
) : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>(){

    lateinit var itemClickListener: ItemClickListener<Any>
     class DataViewHolder(private val binding :TopHeadlineItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: APIArticle, itemClickListener : ItemClickListener<Any>){
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name

            Glide.with(binding.imageViewBanner.context)         //todo: what diff bw img context vs activity context
                .load(article.imageUrl)
                .into(binding.imageViewBanner)

            itemView.setOnClickListener {
                itemClickListener(bindingAdapterPosition,article)
            }
        }
         fun bindDBitem(article: Article, itemClickListener: ItemClickListener<Any>) {
             binding.textViewTitle.text = article.title
             binding.textViewDescription.text = article.description
             binding.textViewSource.text = article.source.name

             Glide.with(binding.imageViewBanner.context).load(article.imageUrl)
                 .into(binding.imageViewBanner)

             itemView.setOnClickListener {
                 itemClickListener(bindingAdapterPosition, article)
             }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        TopHeadlineItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        if (articleList[position] is APIArticle) {
            holder.bind(articleList[position] as APIArticle, itemClickListener = itemClickListener)
        }else {
            holder.bindDBitem(articleList[position] as Article, itemClickListener = itemClickListener)
        }
    }

    fun addData(list: List<Any>) {
        articleList.addAll(list)
    }
}