package com.aboutobjects.curriculum.readinglist.model

data class ReadingList(
    val title: String? = null,
    val bookIds: List<Int> = emptyList(),
    val id: Int
)