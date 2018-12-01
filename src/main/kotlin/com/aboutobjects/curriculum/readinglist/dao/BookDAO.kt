package com.aboutobjects.curriculum.readinglist.dao

import com.aboutobjects.curriculum.readinglist.model.Book
import com.aboutobjects.curriculum.readinglist.model.OnDiskBook
import java.util.concurrent.atomic.AtomicInteger

class BookDAO(val onDiskBooks: List<OnDiskBook>) {
    val data: MutableMap<Int, Book> = onDiskBooks
        .distinctBy { it.id }
        .map { it -> Book(
            id = it.id,
            title = it.title,
            year = it.year,
            authorId = it.author.id
        ) }
        .map { it.id to it  }
        .toMap()
        .toMutableMap()

    var lastId: AtomicInteger = AtomicInteger(data.size - 1)

    fun save(title: String? = null, authorId: Int, year: String? = null) {
        val id = lastId.incrementAndGet()
        data[id] = Book(
            title = title,
            authorId = authorId,
            year = year,
            id = id
        )
    }

    fun findById(id: Int): Book? {
        return data[id]
    }

    fun update(id: Int, title: String? = null, authorId: Int, year: String? = null) {
        data[id] = Book(
            title = title,
            authorId = authorId,
            year = year,
            id = id
        )
    }

    fun delete(id: Int) {
        data.remove(id)
    }
}