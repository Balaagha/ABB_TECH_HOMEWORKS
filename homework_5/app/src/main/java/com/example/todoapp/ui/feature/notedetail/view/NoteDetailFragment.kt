package com.example.todoapp.ui.feature.notedetail.view

import androidx.navigation.fragment.navArgs
import com.example.todoapp.data.database.feature.note.model.Note
import com.example.todoapp.databinding.FragmentNoteDetailBinding
import com.example.todoapp.framework.BaseMvvmFragment
import com.example.todoapp.ui.feature.notedetail.viewmodel.NoteDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : BaseMvvmFragment<FragmentNoteDetailBinding, NoteDetailViewModel>(
    FragmentNoteDetailBinding::inflate,
    NoteDetailViewModel::class
) {
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun setup() {
        viewModel.getNote(args.noteId).also {
            initUiStartedData(it)
        }
    }

    private fun initUiStartedData(note: Note) {
        binding.apply {
            this.tvTitle.text = note.title
            this.tvBody.text = note.body
        }
    }

}