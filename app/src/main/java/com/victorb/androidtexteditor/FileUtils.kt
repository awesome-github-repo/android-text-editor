package com.victorb.androidtexteditor

import android.content.Context
import android.widget.Toast
import androidx.documentfile.provider.DocumentFile
import com.anggrayudi.storage.file.inPrimaryStorage
import com.anggrayudi.storage.file.isReadOnly
import com.anggrayudi.storage.file.openInputStream
import com.anggrayudi.storage.file.openOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter

fun getFileContent(context: Context, file: DocumentFile): String {
    val sb = StringBuilder()
    val bf = file.openInputStream(context)!!.bufferedReader()
    var line = bf.readLine()
    while (line != null) {
        sb.append(line + "\n")
        line = bf.readLine()
    }
    return sb.toString()
}

fun writeToFile(context: Context, file: DocumentFile, content: String) {
    PrintWriter(file.openOutputStream(context)!!).use { p ->
        p.println(content)
    }
}