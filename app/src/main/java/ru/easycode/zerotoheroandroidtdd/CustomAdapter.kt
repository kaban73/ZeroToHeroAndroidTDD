package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ListItemBinding
class CustomAdapter :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    private val itemList = ArrayList<CharSequence>()
    fun add(source: CharSequence) {
        itemList.add(source)
        notifyItemChanged(itemList.size - 1)
    }
    fun save(bundle: Bundle) {
        bundle.putCharSequenceArrayList(KEY, itemList)
    }
    fun restore(bundle: Bundle) {
        itemList.addAll(bundle.getCharSequenceArrayList(KEY) ?: ArrayList<CharSequence>())
        notifyItemRangeChanged(0, itemList.size)
    }
    companion object {
        private const val KEY = "key"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int =itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    class MyViewHolder(private val binding: ListItemBinding) : ViewHolder(binding.root) {
        fun bind(source:CharSequence) {
            binding.elementTextView.text = source
        }
    }

}