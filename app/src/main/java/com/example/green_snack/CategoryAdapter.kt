package com.example.green_snack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.green_snack.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val items: List<CategoryItem>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val selectedPositions = mutableSetOf<Int>()

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryItem: CategoryItem, isSelected: Boolean) {
            binding.itemImage.setImageResource(categoryItem.imageResId)
            binding.itemTitle.text = categoryItem.title

            if (isSelected) {
                binding.root.background = ContextCompat.getDrawable(binding.root.context, R.drawable.selected_item_background)
                binding.itemTitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            } else {
                binding.root.background = ContextCompat.getDrawable(binding.root.context, R.drawable.item_background)
                binding.itemTitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
            }

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (selectedPositions.contains(position)) {
                    selectedPositions.remove(position)
                } else {
                    selectedPositions.add(position)
                }
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position], selectedPositions.contains(position))
    }

    override fun getItemCount(): Int = items.size

    fun getSelectedCategories(): List<String> {
        return selectedPositions.map { items[it].title }
    }
}

