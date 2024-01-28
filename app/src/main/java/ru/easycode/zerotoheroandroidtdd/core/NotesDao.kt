package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteCache)
    @Query("select * from notes_table where folderId = :folderId")
    suspend fun notes(folderId: Long): List<NoteCache>
    @Query("delete from notes_table where id = :noteId")
    suspend fun delete(noteId: Long)
    @Query("delete from notes_table where folderId = :folderId")
    suspend fun deleteByFolderId(folderId: Long)
    @Query("select * from notes_table where id = :noteId")
    suspend fun note(noteId: Long): NoteCache

}