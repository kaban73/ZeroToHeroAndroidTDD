package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle

interface BundleWrapper {
    interface Save{
        fun save(string: String)
    }
    interface Restore{
        fun restore() : String
    }
    interface Mutable : Save, Restore
    class Base(private val bundle: Bundle) : Mutable {
        override fun save(string: String) {
            bundle.putString(KEY, string)
        }
        override fun restore(): String {
            return bundle.getString(KEY).toString()
        }
    }
    companion object {
        private const val KEY = "key"
    }
}