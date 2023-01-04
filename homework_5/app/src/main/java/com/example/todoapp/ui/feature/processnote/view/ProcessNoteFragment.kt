package com.example.todoapp.ui.feature.processnote.view

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.data.database.feature.note.model.Note
import com.example.todoapp.databinding.FragmentProcessNoteBinding
import com.example.todoapp.framework.BaseMvvmFragment
import com.example.todoapp.ui.feature.processnote.viewmodel.ProcessNoteViewModel
import com.example.todoapp.utils.extentions.isNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProcessNoteFragment : BaseMvvmFragment<FragmentProcessNoteBinding, ProcessNoteViewModel>(
    FragmentProcessNoteBinding::inflate,
    ProcessNoteViewModel::class
) {
    private val args: ProcessNoteFragmentArgs by navArgs()

    private val selectedNote by lazy {
        args.selectedNoteArg
    }

    private val isUpdatePage by lazy {
        selectedNote != null
    }

    override fun setup() {
        initUiStartedData()
        initViewListener()
    }

    private fun initUiStartedData() {
        binding.apply {
            btnCreateOrUpdate.text =
                if (isUpdatePage) getString(R.string.update_task_btn) else getString(R.string.create_task_btn)
            tvPageTitle.text =
                if (isUpdatePage) getString(R.string.update_title) else getString(R.string.create_title)
            editTvTitle.setText(selectedNote?.title)
            editTvDescription.setText(selectedNote?.body)
        }
    }

    private fun initViewListener() {
        binding.apply {
            btnCreateOrUpdate.setOnClickListener {
                validateInputs { changedNote ->
                    if (isUpdatePage && selectedNote?.id.isNotNull()) {
                        viewModel.updateNote(changedNote)
                    } else {
                        viewModel.addNote(changedNote)
                    }
                    findNavController().popBackStack()
                }

            }
        }
    }

    private fun validateInputs(operationBlock: (Note) -> Unit) {
        val changedTitle = binding.editTvTitle.text.toString()
        val changedBody = binding.editTvDescription.text.toString()
        if (changedTitle.isBlank()) {
            viewModel.showSnackBar(
                getString(R.string.empty_title_error_message)
            )
        } else {
            operationBlock.invoke(
                Note(
                    id = if (isUpdatePage) selectedNote?.id ?: 0 else 0,
                    title = changedTitle,
                    body = changedBody
                )
            )
        }
    }


}