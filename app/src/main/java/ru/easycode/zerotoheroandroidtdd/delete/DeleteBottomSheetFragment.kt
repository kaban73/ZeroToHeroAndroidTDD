package ru.easycode.zerotoheroandroidtdd.delete

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel

class DeleteBottomSheetFragment : BottomSheetDialogFragment(R.layout.delete_layout) {
    companion object {
        fun newInstance(itemId : Long) : DeleteBottomSheetFragment {
            val instance = DeleteBottomSheetFragment()
            instance.arguments = Bundle().apply {
                putLong(KEY, itemId)
            }
            return instance
        }
        private const val KEY = "itemIdKey"
    }

    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var viewModel: DeleteViewModel
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(DeleteViewModel::class.java)
        onBackPressedCallback = object :OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemId = requireArguments().getLong(KEY)
        view.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            viewModel.delete(itemId)
            dismiss()
        }

        val textView = view.findViewById<TextView>(R.id.itemTitleTextView)
        viewModel.liveData.observe(viewLifecycleOwner) {
            textView.text = it
        }
        viewModel.init(itemId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}