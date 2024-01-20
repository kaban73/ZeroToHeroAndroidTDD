package ru.easycode.zerotoheroandroidtdd.list

import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.delete.DeleteItemUi

data class ItemUi(
    private val id : Long,
    private val text : String
) {
    fun areItemsSame(other : ItemUi) = id == other.id
    fun delete(deleteItemUi : DeleteItemUi) = deleteItemUi.delete(id)
    fun show(textView : TextView) = textView.setText(text)
}