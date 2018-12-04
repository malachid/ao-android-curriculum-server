package com.aboutobjects.curriculum.readinglist.dao

import com.aboutobjects.curriculum.readinglist.model.OnDiskReadingList
import com.aboutobjects.curriculum.readinglist.model.ReadingList
import java.util.concurrent.atomic.AtomicInteger

class ReadingListDAO(val onDiskList: OnDiskReadingList, val booksDAO: BookDAO) {
    val data: MutableMap<Int?, ReadingList> = mutableMapOf(
        onDiskList.id to ReadingList(
            id = onDiskList.id,
            title = onDiskList.title,
            bookIds = booksDAO.data.keys.toList()
        )
    )

    var lastId: AtomicInteger = AtomicInteger(data.size - 1)

    fun save(title: String? = null, bookIds: List<Int> = emptyList()): ReadingList {
        val id = lastId.incrementAndGet()
        ReadingList(
            title = title,
            bookIds = bookIds,
            id = id
        ).let {
            data[id] = it
            return it
        }
    }

    fun findById(id: Int): ReadingList? {
        return data[id]
    }

    fun complete(): Map<Int?, ReadingList> {
        return data.map {
            val source = it.value
            it.key to source?.copy(
                bookIds = null,
                books = source
                    .bookIds.orEmpty()
                    .mapNotNull {
                        booksDAO.complete(it)
                    }
                    .toList()
            )
        }.toMap()
    }

    fun complete(id: Int): ReadingList? {
        val source = data[id]
        return source?.copy(
            bookIds = null,
            books = source
                .bookIds.orEmpty()
                .mapNotNull {
                    booksDAO.complete(it)
                }
                .toList()
        )
    }

    fun update(id: Int, title: String? = null, bookIds: List<Int> = emptyList()): ReadingList {
        ReadingList(
            title = title,
            bookIds = bookIds,
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