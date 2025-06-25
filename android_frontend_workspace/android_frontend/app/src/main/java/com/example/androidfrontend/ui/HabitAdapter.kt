package com.example.androidfrontend.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfrontend.data.Habit
import com.example.androidfrontend.databinding.ItemHabitBinding

class HabitAdapter(
    private val onCheckChanged: (Habit, Boolean) -> Unit,
    private val onEdit: (Habit) -> Unit
) : ListAdapter<Habit, HabitAdapter.HabitViewHolder>(HabitDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder =
        HabitViewHolder(
            ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HabitViewHolder(private val binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            binding.habitName.text = habit.name
            binding.habitDescription.text = habit.description ?: ""
            binding.checkBox.isChecked = habit.isChecked
            binding.checkBox.setOnCheckedChangeListener(null)
            binding.checkBox.setOnCheckedChangeListener { _, checked ->
                onCheckChanged(habit, checked)
            }
            binding.btnEdit.setOnClickListener { onEdit(habit) }
        }
    }

    object HabitDiff : DiffUtil.ItemCallback<Habit>() {
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Habit, newItem: Habit) = oldItem == newItem
    }
}
