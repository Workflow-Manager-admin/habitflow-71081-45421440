package com.example.androidfrontend.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfrontend.R
import com.example.androidfrontend.data.Habit
import com.example.androidfrontend.databinding.FragmentHabitListBinding

/**
 * Fragment displaying the list of habits, each with a checkbox for checking off daily completion.
 */
class HabitListFragment : Fragment() {
    private var _binding: FragmentHabitListBinding? = null
    private val binding get() = _binding!!
    private val habitViewModel: HabitViewModel by viewModels()
    private val adapter = HabitAdapter(
        onCheckChanged = { habit, checked -> habitViewModel.setHabitChecked(habit.id, checked) },
        onEdit = { habit -> onEditHabit?.invoke(habit) }
    )

    /** Exposed callback to parent for edit requests. */
    var onEditHabit: ((Habit) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.habitRecycler.layoutManager = LinearLayoutManager(context)
        binding.habitRecycler.adapter = adapter
        habitViewModel.habits.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.emptyView.visibility = if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
        }
        binding.fabAddHabit.setOnClickListener {
            onEditHabit?.invoke(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
