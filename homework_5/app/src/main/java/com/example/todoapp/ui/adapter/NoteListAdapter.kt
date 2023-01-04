package com.example.todoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.database.feature.note.model.Note
import com.example.todoapp.databinding.ItemNoteLayoutBinding

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_note_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MyViewHolder(private val noteItemBinding: ItemNoteLayoutBinding) :
        RecyclerView.ViewHolder(noteItemBinding.root) {
        fun bind(note: Note) {
            noteItemBinding.noteItem = note
            noteItemBinding.executePendingBindings()

            noteItemBinding.root.setOnClickListener {
                onItemClickListenerForUpdate?.let {
                    it(note)
                }
            }

            noteItemBinding.leftPrimary.setOnClickListener {
                onItemClickListenerForDetail?.let {
                    it(note.id)
                }
            }

        }
    }

    private var onItemClickListenerForUpdate: ((Note) -> Unit)? = null
    private var onItemClickListenerForDetail: ((Long) -> Unit)? = null

    fun setOnItemClickListenerForUpdate(listener: (Note) -> Unit) {
        onItemClickListenerForUpdate = listener
    }
    fun setOnItemClickListenerForDetail(listener: (Long) -> Unit) {
        onItemClickListenerForDetail = listener
    }

}