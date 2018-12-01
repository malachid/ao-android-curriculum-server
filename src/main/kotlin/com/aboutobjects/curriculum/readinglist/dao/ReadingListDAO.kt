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

    fun save(title: String? = null, bookIds: List<Int> = emptyList()) {
        val id = lastId.incrementAndGet()
        data[id] = ReadingList(
            title = title,
            bookIds = bookIds,
            id = id
        )
    }

    fun findById(id: Int): ReadingList? {
        return data[id]
    }

    fun update(id: Int, title: String? = null, bookIds: List<Int> = emptyList()) {
        data[id] = ReadingList(
            title = title,
            bookIds = bookIds,
            id = id
        )
    }

    fun delete(id: Int) {
        data.remove(id)
    }

}