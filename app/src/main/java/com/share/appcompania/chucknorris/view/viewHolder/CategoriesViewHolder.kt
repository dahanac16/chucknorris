package com.share.appcompania.chucknorris.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.share.appcompania.chucknorris.databinding.ItemCategoryBinding

class CategoriesViewHolder (binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {

    var binding: ItemCategoryBinding

    init {
        this.binding = binding
    }

    fun bind(text: String){
        binding.text.text = text
    }
}