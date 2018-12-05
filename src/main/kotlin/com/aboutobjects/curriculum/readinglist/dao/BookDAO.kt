package com.aboutobjects.curriculum.readinglist.dao

import com.aboutobjects.curriculum.readinglist.model.Book
import com.aboutobjects.curriculum.readinglist.model.OnDiskBook
import java.util.concurrent.atomic.AtomicInteger

class BookDAO(val onDiskBooks: List<OnDiskBook>, val authors: AuthorDAO) {
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

    fun save(title: String? = null, authorId: Int, year: String? = null): Book {
        val id = lastId.incrementAndGet()
        Book(
            title = title,
            authorId = authorId,
            year = year,
            id = id
        ).let {
            data[id] = it
            return it
        }
    }

    fun findById(id: Int): Book? {
        return data[id]
    }

    fun complete(): Map<Int, Book> {
        return data.map {
            val source = it.value
            it.key to source.copy(
                authorId = null,
                author = source.authorId?.let { authors.findById(source.authorId) }
            )
        }.toMap()
    }

    fun complete(id: Int): Book? {
        return complete(data[id])
    }

    fun complete(source: Book?): Book? {
        return source?.copy(
            authorId = null,
            author = source.authorId?.let { authors.findById(it) }
        )
    }

    fun update(id: Int, title: String? = null, authorId: Int, year: String? = null): Book {
        Book(
            title = title,
            authorId = authorId,
            year = year,
            id = id
        ).let {
            data[id] = it
            return it
        }
    }

    fun delete(id: Int) {
        data.remove(id)
    }
}