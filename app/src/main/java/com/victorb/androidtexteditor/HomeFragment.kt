package com.victorb.androidtexteditor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.anggrayudi.storage.SimpleStorageHelper

class HomeFragment : Fragment() {
    private val fileRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.button_new_file).setOnClickListener {
            Toast.makeText(activity, "TODO", Toast.LENGTH_LONG).show()
        }

        view.findViewById<Button>(R.id.button_select_file).setOnClickListener {
            (requireActivity() as MainActivity).storageHelper.onFileSelected = { _: Int, documentFile: DocumentFile ->
                println("SWITCHING ACTIVITY")
                requireActivity().findNavController(R.id.fragment2).navigate(
                    HomeFragmentDirections.actionHomeFragmentToEditFragment(documentFile.uri.toString()))
            }
            (requireActivity() as MainActivity).storageHelper.openFilePicker(fileRequestCode, "text/*")
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}