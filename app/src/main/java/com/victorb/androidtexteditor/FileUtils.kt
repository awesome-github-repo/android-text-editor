package com.victorb.androidtexteditor

import android.content.Context
import android.widget.Toast
import androidx.documentfile.provider.DocumentFile
import com.anggrayudi.storage.file.isReadOnly
import com.anggrayudi.storage.file.openInputStream
import com.anggrayudi.storage.file.openOutputStream
import java.io.FileNotFoundException
import java.io.PrintWriter

fun getFileContent(context: Context, documentFile: DocumentFile): String {
    val sb = StringBuilder()
    val bf = documentFile.openInputStream(context)!!.bufferedReader()
    var line = bf.readLine()
    while (line != null) {
        sb.append(line + "\n")
        line = bf.readLine()
    }
    println("CONTENT")
    println(sb.toString())
    return sb.toString()
}

fun writeToFile(context: Context, documentFile: DocumentFile, content: String) {
    println("THE URI IS : " + documentFile.uri)
    PrintWriter(documentFile.openOutputStream(context)!!).use { p ->
        p.println(content)
    }
}