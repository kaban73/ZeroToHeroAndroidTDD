package ru.easycode.zerotoheroandroidtdd.list

import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.details.DetailsItemUi

data class ItemUi(
    private val id: Long,
    private var text : String
) {
    fun areItemsSame(item: ItemUi) =  id == item.id
    fun areContentsTheSame(item: ItemUi) = text == item.text
    fun detailsItem(detailsItemUi: DetailsItemUi) = detailsItemUi.details(id)
    fun show(textView: TextView) = textView.setText(text)
}