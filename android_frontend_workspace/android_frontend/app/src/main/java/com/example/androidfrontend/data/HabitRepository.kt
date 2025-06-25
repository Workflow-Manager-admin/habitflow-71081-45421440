package com.example.androidfrontend.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Repository for managing habits in-memory.
 * Replace with persistent storage for production use.
 */
object HabitRepository {
    private val habits = mutableListOf<Habit>()
    private val habitsLiveData = MutableLiveData<List<Habit>>(habits)

    /**
     * PUBLIC_INTERFACE
     * Returns a live list of habits.
     */
    fun getHabits(): LiveData<List<Habit>> = habitsLiveData

    /**
     * PUBLIC_INTERFACE
     * Adds a new habit.
     */
    fun addHabit(habit: Habit) {
        habits.add(habit)
        habitsLiveData.value = habits.toList()
    }

    /**
     * PUBLIC_INTERFACE
     * Updates an existing habit.
     */
    fun updateHabit(updated: Habit) {
        val idx = habits.indexOfFirst { it.id == updated.id }
        if (idx >= 0) {
            habits[idx] = updated
            habitsLiveData.value = habits.toList()
        }
    }

    /**
     * PUBLIC_INTERFACE
     * Marks a habit checked for today.
     */
    fun setHabitChecked(id: Long, checked: Boolean) {
        val idx = habits.indexOfFirst { it.id == id }
        if (idx >= 0) {
            habits[idx].isChecked = checked
            habitsLiveData.value = habits.toList()
        }
    }
}
