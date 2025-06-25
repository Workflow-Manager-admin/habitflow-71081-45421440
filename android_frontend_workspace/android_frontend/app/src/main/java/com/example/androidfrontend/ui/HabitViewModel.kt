package com.example.androidfrontend.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidfrontend.data.Habit
import com.example.androidfrontend.data.HabitRepository

// PUBLIC_INTERFACE
class HabitViewModel : ViewModel() {
    /** Exposes the list of habits as LiveData for observation by UI. */
    val habits: LiveData<List<Habit>> = HabitRepository.getHabits()

    // PUBLIC_INTERFACE
    fun addHabit(habit: Habit) {
        HabitRepository.addHabit(habit)
    }

    // PUBLIC_INTERFACE
    fun updateHabit(habit: Habit) {
        HabitRepository.updateHabit(habit)
    }

    // PUBLIC_INTERFACE
    fun setHabitChecked(habitId: Long, isChecked: Boolean) {
        HabitRepository.setHabitChecked(habitId, isChecked)
    }
}
