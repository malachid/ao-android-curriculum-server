package com.aboutobjects.curriculum.readinglist.model

data class ReadingList(
    // Title of the reading list
    val title: String? = null,
    // By default, only include a list of bookIds
    val bookIds: List<Int>? = null,
    // Optionally, include the full book references instead
    val books: Map<Int, Book>? = null,
    // id of the reading list
    val id: Int
)