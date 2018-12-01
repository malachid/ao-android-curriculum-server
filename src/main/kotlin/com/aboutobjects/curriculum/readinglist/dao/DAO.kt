package com.aboutobjects.curriculum.readinglist.dao

import com.aboutobjects.curriculum.readinglist.model.OnDiskBook
import com.aboutobjects.curriculum.readinglist.model.OnDiskReadingList
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.FileReader

class DAO {
    companion object {
        const val JSON_FILE = "BooksAndAuthors.json"

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()
    }

    /**
     * We are sourcing a (single) nested json and converting to three separate maps
     */
    private val sourceReader = BufferedReader(FileReader(JSON_FILE))
    private val onDiskList: OnDiskReadingList = gson.fromJson(sourceReader, OnDiskReadingList::class.java)
    private val onDiskBooks: List<OnDiskBook> = onDiskList.books

    /**
     * Storing these in-memory as we want to start fresh whenever the server is restarted.
     */
    val authors = AuthorDAO(onDiskBooks)
    val books = BookDAO(onDiskBooks)
    val readingList = ReadingListDAO(onDiskList, books)
}