package com.victorb.androidtexteditor

import android.content.Context
import androidx.documentfile.provider.DocumentFile
import com.anggrayudi.storage.file.openInputStream
import com.anggrayudi.storage.file.openOutputStream
import java.io.FileNotFoundException
import java.io.PrintWriter

fun getFileContent(context: Context, documentFile: DocumentFile): String {
    val sb = StringBuilder()
    val bf = documentFile.openInputStream(context)!!.bufferedReader()
    var line = bf.readLine()
    while (line != null) {
        sb.append(line)
        line = bf.readLine()
    }
    return sb.toString()
}

fun writeToFile(context: Context, documentFile: DocumentFile, content: String) {
    try {
        PrintWriter(documentFile.openOutputStream(context)!!).use { p -> p.println(content) }
    } catch (e1: FileNotFoundException) {
        e1.printStackTrace()
    }
}