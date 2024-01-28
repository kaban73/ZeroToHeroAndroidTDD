package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.create.CreateFolderViewModel
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.folder.details.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.edit.EditFolderViewModel
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.note.core.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.core.Now
import ru.easycode.zerotoheroandroidtdd.note.create.CreateNoteViewModel
import ru.easycode.zerotoheroandroidtdd.note.edit.EditNoteViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T
    class Factory(
        private val provideViewModel: ProvideViewModel
    ) : ProvideViewModel, ClearViewModels {
        private val map = mutableMapOf<Class<out ViewModel>, ViewModel>()
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            val viewModel = map[viewModelClass]
            return if (viewModel==null)
                provideViewModel.viewModel(viewModelClass).also {
                    map[viewModelClass] = it
                }
            else
                viewModel as T
        }
        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            viewModelClasses.forEach { map.remove(it) }
        }
    }
    class Base(
        core : Core,
        private val clearViewModels: ClearViewModels
    ) : ProvideViewModel {
        private val navigation = Navigation.Base()
        private val folderListLiveDataWrapper = FolderListLiveDataWrapper.Base()
        private val folderLiveDataWrapper = FolderLiveDataWrapper.Base()
        private  val foldersRepository = FoldersRepository.Base(Now.Base(), core.foldersDao(), core.notesDao())
        private val noteListLiveDataWrapper = NoteListLiveDataWrapper.Base()
        private val noteLiveDataWrapper = NoteLiveDataWrapper.Base()
        private val notesRepository = NotesRepository.Base(Now.Base(), core.notesDao())
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T = when(viewModelClass){
            MainViewModel::class.java -> MainViewModel(navigation)
            FolderListViewModel::class.java -> FolderListViewModel(folderListLiveDataWrapper, folderLiveDataWrapper, foldersRepository, navigation)
            FolderDetailsViewModel::class.java -> FolderDetailsViewModel(noteListLiveDataWrapper, folderLiveDataWrapper, notesRepository, navigation,clearViewModels)
            CreateFolderViewModel::class.java -> CreateFolderViewModel(folderListLiveDataWrapper,foldersRepository,navigation,clearViewModels)
            EditFolderViewModel::class.java->EditFolderViewModel(folderLiveDataWrapper,foldersRepository,navigation,clearViewModels)
            CreateNoteViewModel::class.java -> CreateNoteViewModel(folderLiveDataWrapper,noteListLiveDataWrapper,notesRepository,navigation,clearViewModels)
            EditNoteViewModel::class.java->EditNoteViewModel(folderLiveDataWrapper,noteLiveDataWrapper,noteListLiveDataWrapper,notesRepository,navigation,clearViewModels)
            else -> throw IllegalStateException("unknown viewModelClass $viewModelClass")
        } as T

    }
}