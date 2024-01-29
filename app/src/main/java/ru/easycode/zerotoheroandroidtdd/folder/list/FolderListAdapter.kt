package ru.easycode.zerotoheroandroidtdd.folder.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.FolderListItemBinding
import ru.easycode.zerotoheroandroidtdd.folder.details.DetailsFolderUi

class FolderListAdapter(
    private val detailsFolderUi: DetailsFolderUi
) : RecyclerView.Adapter<FolderViewHolder>() {
    private val list : MutableList<FolderUi> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder =
        FolderViewHolder(FolderListItemBinding.inflate(LayoutInflater.from(parent.context)),
            detailsFolderUi)

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int =
        list.size
    fun update(newList : List<FolderUi>) {
        val myDiffUtilCallback = MyDiffUtilCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(myDiffUtilCallback)
        list.clear().also { list.addAll(newList) }
        diffResult.dispatchUpdatesTo(this)
    }
}
class MyDiffUtilCallback(
    private val oldList : List<FolderUi>,
    private val newList : List<FolderUi>
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
class FolderViewHolder(
    private val folderListItemBinding: FolderListItemBinding,
    private val detailsFolderUi: DetailsFolderUi
) : RecyclerView.ViewHolder(folderListItemBinding.root) {
    fun bind(folderUi: FolderUi) {
        folderUi.show(folderListItemBinding.folderTitleTextView,
            folderListItemBinding.folderCountTextView)
        itemView.setOnClickListener {
            folderUi.detailsFolderUi(detailsFolderUi)
        }
    }
}