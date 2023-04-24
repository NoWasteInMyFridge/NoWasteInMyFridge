package com.develop.nowasteinmyfridge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.develop.nowasteinmyfridge.databinding.ListItemBinding
import com.develop.nowasteinmyfridge.model.Meal
import javax.inject.Inject


class NoWasteInMyFrideAdapter @Inject constructor():
     ListAdapter<Meal, NoWasteInMyFrideAdapter.NoWasteInMyFrideViewHolder>(NoWasteInMyFrideDiffCallback()) {

    class NoWasteInMyFrideViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Meal) {
            binding.apply {
                meal = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoWasteInMyFrideViewHolder {
        return NoWasteInMyFrideViewHolder(ListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: NoWasteInMyFrideViewHolder, position: Int) {
        val meal = getItem(position)
        holder.onBind(meal)
    }

}
private class NoWasteInMyFrideDiffCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}