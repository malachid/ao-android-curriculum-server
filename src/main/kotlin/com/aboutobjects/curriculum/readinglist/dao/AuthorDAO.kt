package com.aboutobjects.curriculum.readinglist.dao

import com.aboutobjects.curriculum.readinglist.model.Author
import com.aboutobjects.curriculum.readinglist.model.OnDiskBook
import java.util.concurrent.atomic.AtomicInteger

class AuthorDAO(val onDiskBooks: List<OnDiskBook>) {
    val data: MutableMap<Int, Author> = onDiskBooks
        .map { it -> it.author }
        .distinctBy { it.id }
        .map { it.id to it  }
        .toMap()
        .toMutableMap()

    var lastId: AtomicInteger = AtomicInteger( data.size - 1)

    fun save(firstName: String, lastName: String) {
        val id = lastId.incrementAndGet()
        data[id] = Author(
            firstName = firstName,
            lastName =  lastName,
            id = id
        )
    }

    fun findById(id: Int): Author? {
        return data[id]
    }

    fun update(id: Int, firstName: String, lastName: String) {
        data[id] = Author(
            firstName = firstName,
            lastName =  lastName,
            id = id
        )
    }

    fun delete(id: Int) {
        data.remove(id)
    }
}