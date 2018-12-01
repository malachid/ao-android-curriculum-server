package com.aboutobjects.curriculum.readinglist.model

data class OnDiskBook(
    val title: String? = null,
    val author: Author,
    val year: String? = null,
    val id: Int
)