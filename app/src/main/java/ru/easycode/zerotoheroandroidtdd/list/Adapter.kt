package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemListBinding
import ru.easycode.zerotoheroandroidtdd.details.DetailsItemUi

class Adapter(private val detailsItemUi: DetailsItemUi) : RecyclerView.Adapter<MyViewHolder>() {
    private val list : MutableList<ItemUi> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)), detailsItemUi)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
    fun update(newList : List<ItemUi>) {
        val myDiffUtilCallback = MyDiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(myDiffUtilCallback)
        list.clear().also { list.addAll(newList) }
        diff.dispatchUpdatesTo(this)
    }
}
class MyDiffUtilCallback(
    private val oldList: List<ItemUi>,
    private val newList: List<ItemUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].areItemsSame(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].areContentsTheSame(newList[newItemPosition])

}
class MyViewHolder(
    private val itemListBinding: ItemListBinding,
    private val detailsItemUi: DetailsItemUi)
    : RecyclerView.ViewHolder(itemListBinding.root) {
    fun bind(itemUi : ItemUi){
        itemUi.show(itemListBinding.elementTextView)
        itemView.setOnClickListener {
            itemUi.detailsItem(detailsItemUi)
        }
    }
}