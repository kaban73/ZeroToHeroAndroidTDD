package ru.easycode.zerotoheroandroidtdd.note.core

interface NoteLiveDataWrapper {
    fun update(noteText: String)
    class Base : NoteLiveDataWrapper {
        override fun update(noteText: String) {
            TODO("Not yet implemented")
        }

    }
}