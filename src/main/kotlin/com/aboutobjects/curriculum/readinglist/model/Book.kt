package com.aboutobjects.curriculum.readinglist.model

data class Book(
    // Title of the book
    val title: String? = null,
    // By default, only include the author id
    val authorId: Int? = null,
    // Optionally, include the entire author reference instead
    val author: Author? = null,
    // Year the book was published
    val year: String? = null,
    // id of the Book
    val id: Int
)