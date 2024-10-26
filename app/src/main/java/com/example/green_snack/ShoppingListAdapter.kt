// ShoppingListAdapter.kt
package com.example.green_snack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.green_snack.databinding.ItemShoppingListBinding

class ShoppingListAdapter(private val items: MutableList<ShoppingItem>) :
    RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    inner class ShoppingListViewHolder(private val binding: ItemShoppingListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShoppingItem) {
            binding.itemName.text = item.name
            binding.itemCheckbox.isChecked = item.isChecked

            binding.itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                item.isChecked = isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val binding = ItemShoppingListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShoppingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
