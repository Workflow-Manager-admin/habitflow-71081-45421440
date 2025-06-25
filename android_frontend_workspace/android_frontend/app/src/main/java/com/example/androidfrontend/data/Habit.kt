package com.example.androidfrontend.data

import java.io.Serializable

/**
 * Data model representing a Habit.
 * @property id Unique identifier for the habit.
 * @property name Name of the habit.
 * @property description Optional description of the habit.
 * @property isChecked Whether the habit has been checked off for today.
 */
data class Habit(
    val id: Long,
    var name: String,
    var description: String? = null,
    var isChecked: Boolean = false
) : Serializable
