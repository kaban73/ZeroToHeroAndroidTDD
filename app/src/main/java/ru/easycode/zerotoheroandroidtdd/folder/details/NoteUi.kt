package ru.easycode.zerotoheroandroidtdd.folder.details

import android.widget.TextView

data class NoteUi(
    val id : Long,
    val title : String,
    val folderId : Long
) {
    fun areItemsTheSame(noteUi: NoteUi) = noteUi.id == id
    fun areContentsTheSame(noteUi: NoteUi) = noteUi.title == title
    fun detailsNoteUi(detailsNoteUi: DetailsNoteUi) = detailsNoteUi.detailsNoteUi(this)
    fun show(textView: TextView) = textView.setText(title)
}