package com.aboutobjects.curriculum.readinglist.model

data class Book(
    val title: String? = null,
    val authorId: Int,
    val year: String? = null,
    val id: Int
)