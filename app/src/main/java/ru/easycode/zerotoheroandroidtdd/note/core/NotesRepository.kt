package ru.easycode.zerotoheroandroidtdd.note.core

import ru.easycode.zerotoheroandroidtdd.core.NoteCache
import ru.easycode.zerotoheroandroidtdd.core.NotesDao

interface NotesRepository {
    interface ReadList {
        suspend fun noteList(folderId: Long): List<MyNote>
    }
    interface Edit {
        suspend fun deleteNote(noteId: Long)
        suspend fun renameNote(noteId: Long, text: String)
        suspend fun note(noteId: Long): MyNote
    }
    interface Create {
        suspend fun createNote(folderId: Long, text: String) : Long
    }
    class Base(
        private val now: Now,
        private val dao : NotesDao
    ) : Create, Edit, ReadList {
        override suspend fun createNote(folderId: Long, text: String) : Long {
            val id = now.timeInMillis()
            dao.insert(NoteCache(id, text, folderId))
            return id
        }


        override suspend fun noteList(folderId: Long): List<MyNote> =
            dao.notes(folderId).map { MyNote(it.id,it.text,it.folderId) }

        override suspend fun deleteNote(noteId: Long) =
            dao.delete(noteId)

        override suspend fun renameNote(noteId: Long, text: String) {
            val note = dao.note(noteId)
            val newNote = note.copy(text = text)
            dao.insert(newNote)
        }

        override suspend fun note(noteId: Long): MyNote =
            dao.note(noteId).let { MyNote(it.id,it.text,it.folderId) }

    }
}