package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ItemListBinding

class MyCustomAdapter
    : RecyclerView.Adapter<MyViewHolder>() {
    private val itemList = ArrayList<CharSequence>()
    fun updateList(newList: List<CharSequence>) {
        val diffCallback = ListCallBack(itemList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemList.clear()
        itemList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun getItemCount(): Int = itemList.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}
class MyViewHolder(private val binding: ItemListBinding) : ViewHolder(binding.root) {
    fun bind(source : CharSequence) {
        binding.elementTextView.text = source
    }
}
class ListCallBack(private val oldList: List<CharSequence>,
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