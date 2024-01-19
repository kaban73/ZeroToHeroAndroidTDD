package ru.easycode.zerotoheroandroidtdd.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ItemListBinding

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private val list = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }
    fun update(newList : List<String>) {
        val diffCallback = DiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }
}
class MyViewHolder(private val itemListBinding: ItemListBinding) : ViewHolder(itemListBinding.root) {
    fun bind(text : String) {
        itemListBinding.elementTextView.text = text
    }
}

class DiffUtilCallback(
    private val oldList : List<String>,
    private val newList : List<String>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}