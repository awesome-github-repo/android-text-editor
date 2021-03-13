package com.victorb.androidtexteditor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.documentfile.provider.DocumentFile
import com.anggrayudi.storage.SimpleStorageHelper
import com.anggrayudi.storage.file.*
import com.google.android.material.textfield.TextInputEditText
import java.io.BufferedReader
import java.io.FileReader

class MainActivity : AppCompatActivity() {
    private lateinit var storageHelper: SimpleStorageHelper
    private lateinit var openedFile: DocumentFile

    override fun onCreate(savedInstanceState: Bundle?) {
        // Default actions
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set Simple Storage helper
        storageHelper = SimpleStorageHelper(this, savedInstanceState)

        // When a file is opened
        storageHelper.onFileSelected = { _: Int, documentFile: DocumentFile ->
            openedFile = documentFile
            val content = getFileContent(this, documentFile)
            findViewById<EditText>(R.id.editTextTextMultilineContent).setText(content)
            toolbar.title = documentFile.name
        }
        // Ask for a file
        storageHelper.openFilePicker(1, "text/*")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_save_file -> {
                val content = findViewById<EditText>(R.id.editTextTextMultilineContent).text.toString()
                writeToFile(this, openedFile, content)
                Toast.makeText(this, "File saved successfully", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        storageHelper.storage.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        storageHelper.storage.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        storageHelper.storage.onRestoreInstanceState(savedInstanceState)
    }
}