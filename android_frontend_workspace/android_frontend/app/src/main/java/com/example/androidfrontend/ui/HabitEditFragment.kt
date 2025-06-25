package com.example.androidfrontend.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.androidfrontend.data.Habit
import com.example.androidfrontend.databinding.FragmentHabitEditBinding

class HabitEditFragment : Fragment() {
    private var _binding: FragmentHabitEditBinding? = null
    private val binding get() = _binding!!

    /** Habit to edit or null for new habit. */
    var editingHabit: Habit? = null
    var onHabitSaved: ((Habit) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editingHabit?.let {
            binding.inputName.setText(it.name)
            binding.inputDescription.setText(it.description)
        }

        binding.btnSave.setOnClickListener {
            val name = binding.inputName.text?.toString()?.trim().orEmpty()
            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a habit name.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val desc = binding.inputDescription.text?.toString()
            val habit = editingHabit?.copy(name = name, description = desc)
                ?: Habit(System.currentTimeMillis(), name, desc)
            onHabitSaved?.invoke(habit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
