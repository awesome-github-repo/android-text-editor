package com.victorb.androidtexteditor

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Toast
import androidx.documentfile.provider.DocumentFile
import com.anggrayudi.storage.extension.toDocumentFile

class EditFragment : Fragment() {
    private var uriString: String = ""
    private lateinit var file: DocumentFile

    private lateinit var editTextView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uriString = it.getString("uriString").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)

        editTextView = view.findViewById(R.id.editTextTextMultiLine)

        file = activity?.let { Uri.parse(uriString).toDocumentFile(it) }!!
        editTextView.setText(getFileContent(requireContext(), file))

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_save_file -> {
                val content = editTextView.text.toString()
                writeToFile(requireContext(), file, content)
                Toast.makeText(requireContext(), "File saved successfully", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance(uriString: String) =
            EditFragment().apply {
                arguments = Bundle().apply {
                    putString("uriString", uriString)
                }
            }
    }
}