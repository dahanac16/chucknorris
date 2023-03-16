package com.share.appcompania.chucknorris.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.share.appcompania.chucknorris.databinding.ItemCategoryBinding
import com.share.appcompania.chucknorris.model.CategoriesModel
import com.share.appcompania.chucknorris.view.viewHolder.CategoriesViewHolder

class CategoryAdapter (private val categories: CategoriesModel, private val listener: CategoriesListener) : RecyclerView.Adapter<CategoriesViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        context = parent.context
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categories[position])
        holder.itemView.setOnClickListener {
            listener.onClick(categories[position])
        }
    }

    override fun getItemCount(): Int = categories.size

    interface CategoriesListener {
        fun onClick(category: String)
    }
}