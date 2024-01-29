package ru.easycode.zerotoheroandroidtdd.folder.list

import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.folder.details.DetailsFolderUi

data class FolderUi(
    val id : Long,
    val title : String,
    val notesCount: Int
) {
    fun areItemsTheSame(folderUi: FolderUi) = folderUi.id == id
    fun areContentsTheSame(folderUi: FolderUi) = folderUi.title == title && folderUi.notesCount == notesCount
    fun detailsFolderUi(detailsFolderUi: DetailsFolderUi) = detailsFolderUi.detailsFolderUi(this)
    fun show(titleTextView : TextView, countNotesTextView : TextView) {
        titleTextView.text =title
        countNotesTextView.text = notesCount.toString()
    }
}