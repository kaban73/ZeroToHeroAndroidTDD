package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemListBinding
import ru.easycode.zerotoheroandroidtdd.delete.DeleteItemUi

class MyAdapter(private val deleteItem : DeleteItemUi) : RecyclerView.Adapter<MyViewHolder>() {
    private val list = mutableListOf<ItemUi>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)), deleteItem)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun update(newList : List<ItemUi>) {
        val myDiffUtil = MyDiffUtil(list, newList)
        val diffResult = DiffUtil.calculateDiff(myDiffUtil)
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}
class MyDiffUtil(
    private val oldList: List<ItemUi>,
    private val newList: List<ItemUi>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].areItemsSame(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
class MyViewHolder(
    private val itemListBinding: ItemListBinding,
    private val deleteItem : DeleteItemUi
) : RecyclerView.ViewHolder(itemListBinding.root) {
    fun bind(itemUi: ItemUi) {
       itemUi.show(itemListBinding.elementTextView)
        itemView.setOnClickListener {
            itemUi.delete(deleteItem)
        }
    }
}