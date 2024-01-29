package ru.easycode.zerotoheroandroidtdd.folder.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.NoteListItemBinding

class FolderDetailsAdapter(
    private val detailsNoteUi : DetailsNoteUi
) : RecyclerView.Adapter<NoteViewHolder>() {
    private val list = mutableListOf<NoteUi>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(NoteListItemBinding.inflate(LayoutInflater.from(parent.context)),
            detailsNoteUi)

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int =
        list.size
    fun update(newList : List<NoteUi>) {
        val myDiffUtilCallback = MyDiffUtilCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(myDiffUtilCallback)
        list.clear().also { list.addAll(newList) }
        diffResult.dispatchUpdatesTo(this)
    }
}
class MyDiffUtilCallback(
    private val oldList : List<NoteUi>,
    private val newList : List<NoteUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].areItemsTheSame(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].areContentsTheSame(newList[newItemPosition])

}
class NoteViewHolder(
    private val noteListItemBinding: NoteListItemBinding,
    private val detailsNoteUi: DetailsNoteUi
) : RecyclerView.ViewHolder(noteListItemBinding.root) {
    fun bind(noteUi : NoteUi) {
        noteUi.show(noteListItemBinding.noteTitleTextView)
        itemView.setOnClickListener {
            noteUi.detailsNoteUi(detailsNoteUi)
        }
    }
}