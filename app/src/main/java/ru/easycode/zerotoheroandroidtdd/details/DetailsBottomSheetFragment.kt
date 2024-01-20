package ru.easycode.zerotoheroandroidtdd.details

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel

class DetailsBottomSheetFragment : BottomSheetDialogFragment(R.layout.details_page) {
    companion object {
        fun newInstance(itemId : Long) : DetailsBottomSheetFragment {
            val instance = DetailsBottomSheetFragment()
            instance.arguments = Bundle().apply {
                putLong(KEY, itemId)
            }
            return instance
        }
        private const val KEY = "itemIdKey"
    }
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var viewModel: DetailsViewModel
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(DetailsViewModel::class.java)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
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
        val itemInputEditText = view.findViewById<TextInputEditText>(R.id.itemInputEditText)
        val textView = view.findViewById<TextView>(R.id.itemTextView)
        val updateButton = view.findViewById<Button>(R.id.updateButton)
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)

        val itemId = requireArguments().getLong(KEY)
        viewModel.init(itemId)
        viewModel.liveData.observe(viewLifecycleOwner) {
            textView.text = it
            itemInputEditText.setText(it)
        }
        updateButton.setOnClickListener {
            viewModel.update(itemId, itemInputEditText.text.toString())
            dismiss()
        }
        deleteButton.setOnClickListener {
            viewModel.delete(itemId)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }

}