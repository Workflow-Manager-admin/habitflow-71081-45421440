package com.example.androidfrontend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidfrontend.data.Habit
import com.example.androidfrontend.ui.HabitEditFragment
import com.example.androidfrontend.ui.HabitListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var habitListFragment: HabitListFragment
    private var habitEditFragment: HabitEditFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme) // apply light theme
        setContentView(R.layout.activity_main)

        // Create and show habit list fragment
        habitListFragment = HabitListFragment()
        habitListFragment.onEditHabit = { habit -> openHabitEditor(habit) }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, habitListFragment)
            .commit()
    }

    private fun openHabitEditor(habit: Habit?) {
        val editor = HabitEditFragment()
        editor.editingHabit = habit
        editor.onHabitSaved = { updated ->
            if (habit == null) {
                habitListFragment.habitViewModel.addHabit(updated)
            } else {
                habitListFragment.habitViewModel.updateHabit(updated)
            }
            supportFragmentManager.popBackStack()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, editor)
            .addToBackStack(null)
            .commit()
        habitEditFragment = editor
    }
}
