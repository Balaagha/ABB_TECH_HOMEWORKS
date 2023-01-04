package com.example.todoapp.ui.feature.home.view

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.framework.BaseMvvmFragment
import com.example.todoapp.framework.GenericUiEvent
import com.example.todoapp.ui.adapter.NoteListAdapter
import com.example.todoapp.ui.feature.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class
) {
    @Inject
    @Named("home_fragment")
    lateinit var adapter: NoteListAdapter

    override fun setup() {
        binding.apply {
            viewModel.getAllNote().observe(viewLifecycleOwner) {
                tvEmptyPage.isVisible = it.isEmpty()
                adapter.differ.submitList(it)
            }

            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        }
        initViewListener()
    }

    private fun initViewListener() {
        binding.apply {
            addBtn.setOnClickListener {
                val action = HomeFragmentDirections.actionMainFragmentToProcessNoteFragment()
                findNavController().navigate(action)
            }
            adapter.setOnItemClickListenerForUpdate {
                val action = HomeFragmentDirections.actionMainFragmentToProcessNoteFragment(it)
                findNavController().navigate(action)
            }
            adapter.setOnItemClickListenerForDetail {
                val action = HomeFragmentDirections.actionMainFragmentToNoteDetailFragment(it)
                findNavController().navigate(action)
            }
        }
    }

    private val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            val note = adapter.differ.currentList[position]
            if (note != null) {
                viewModel.deleteNote(note)
                showSnackBarViaBaseUiEvent(GenericUiEvent.SnackBar("Successfully delete "))
            }
        }
    }

}