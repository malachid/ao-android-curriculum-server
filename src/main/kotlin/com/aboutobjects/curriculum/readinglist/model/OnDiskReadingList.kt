package com.aboutobjects.curriculum.readinglist.model

data class OnDiskReadingList(
    val title: String? = null,
    val books: List<OnDiskBook> = emptyList(),
    val id: Int
)