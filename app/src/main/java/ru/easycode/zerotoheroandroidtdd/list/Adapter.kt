package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ListItemBinding

class Adapter : RecyclerView.Adapter<MyViewHolder>() {
    private val list = mutableListOf<CharSequence>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
    fun update(newList : List<CharSequence>) {
        val diffCallback = DiffCallback(list, newList)
        val diff = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }
}
class MyViewHolder(private val itemListBinding: ListItemBinding) : ViewHolder(itemListBinding.root) {
    fun bind(text : CharSequence) {
        itemListBinding.elementTextView.text = text
    }
}
class DiffCallback(
    private val oldList : List<CharSequence>,
    private val newList : List<CharSequence>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}