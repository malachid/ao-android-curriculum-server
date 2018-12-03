package com.aboutobjects.curriculum.readinglist

import com.aboutobjects.curriculum.readinglist.dao.DAO
import org.slf4j.LoggerFactory
import spark.Request
import spark.Spark.*
import java.net.NetworkInterface


val dao = DAO()
val logger = LoggerFactory.getLogger("ReadingList")

fun main(args: Array<String>) {

//    port(9091)

    before("/*") { req, res ->
        logger.info("API ${req.url()} ")
    }

    get("/hello") { req, res ->
        val host = "http://${getAddress()}:${req.port()}"
        """
            <h2>Welcome to the Curriculum RESTful Server</h2>
            Serving on <a href="$host/hello">$host</a>
            <ul>
                <li>Reading Lists: ${dao.readingList.data.size}</li>
                <li>Books: ${dao.books.data.size}</li>
                <li>Authors: ${dao.authors.data.size}</li>
            </ul>
            <h3>Example Requests</h3>
            <p>Below are some example requests. Note: The content is being sent pretty-printed, so while the browser will not show it that way, you can View Source to see a better representation.</p>
            <h4>Default</h4>
            <p>Default is to represent child Books and Authors by their ID only.</p>
            <ul>
                <li><a href="$host/lists">Reading List</a> (<i>/lists</i> using Book ids)</li>
                <li><a href="$host/books">Books</a> (<i>/books</i> using Author ids)</li>
                <li><a href="$host/books/12">Specific Book</a> (<i>/books/12</i> using Author ids)</li>
                <li><a href="$host/authors">Authors</a> (<i>/authors</i>)</li>
                <li><a href="$host/authors/13">Specific Author</a> (<i>/authors/13</i>)</li>
            </ul>
            <h4>Full</h4>
            <p>'?full=true' allow the child IDs to be replaced with fully-populated references.</p>
            <ul>
                <li><a href="$host/lists?full=true">Full Reading List</a> (<i>/lists?full=true</i> using Book and Author ref)</li>
                <li><a href="$host/lists/0?full=true">Full Reading List</a> (<i>/lists/0?full=true</i> using Book and Author ref)</li>
                <li><a href="$host/books?full=true">Full Books</a> (<i>/books?full=true</i> using Author ref)</li>
                <li><a href="$host/books/0?full=true">Full Book</a> (<i>/books/0?full=true</i> using Author ref)</li>
            </ul>
            <h4>Non-GET</h4>
            <p>These can not be represented by a standard GET call in the web browser</p>
            <ul>
                <li>DELETE /lists/delete/1</li>
                <li>POST /lists/create title="My Reading List" bookIds="2,5,10"</li>
                <li>PATCH /lists/update/1 title="My Reading List" bookIds="2,5,10"</li>
                <li>DELETE /books/delete/5</li>
                <li>POST /books/create title="My Title" year="2020" authorId=13</li>
                <li>PATCH /books/update/5 title="My Title" year="2020" authorId=13</li>
                <li>DELETE /authors/delete/5</li>
                <li>POST /authors/create title="My Title" year="2020" authorId=13</li>
                <li>PATCH /authors/update/5 title="My Title" year="2020" authorId=13</li>
            </ul>
        """.trimIndent()
    }

    path("/lists") {
        get("") { req, res ->
            when {
                req.full() -> DAO.gson.toJson(dao.readingList.complete())
                else -> DAO.gson.toJson(dao.readingList.data)
            }
        }

        get("/:id") { req, res ->
            when{
                req.full() -> DAO.gson.toJson(dao.readingList.complete(req.id()))
                else -> DAO.gson.toJson(dao.readingList.findById(req.id()))
            }
        }

        post("/create") { req, res ->
            dao.readingList.save(
                title = req.qp("title"),
                bookIds = req.qp("bookIds")
                    .split(",")
                    .map { it -> it.toInt() }
            )
            res.status(201)
            "ok"
        }

        patch("/update/:id") { req, res ->
            dao.readingList.update(
                id = req.id(),
                title = req.qp("title"),
                bookIds = req.qp("bookIds")
                    .split(",")
                    .map { it -> it.toInt() }
            )
            "ok"
        }

        delete("/delete/:id") { req, res ->
            dao.readingList.delete(id = req.id())
            "ok"
        }
    }

    path("/books") {
        get("") { req, res ->
            when {
                req.full() -> DAO.gson.toJson(dao.books.complete())
                else -> DAO.gson.toJson(dao.books.data)
            }
        }

        get("/:id") { req, res ->
            when {
                req.full() -> DAO.gson.toJson(dao.books.complete(req.id()))
                else -> DAO.gson.toJson(dao.books.findById(req.id()))
            }
        }

        post("/create") { req, res ->
            dao.books.save(
                title = req.qp("title"),
                year = req.qp("year"),
                authorId = req.qp("authorId").toInt()
            )
            res.status(201)
            "ok"
        }

        patch("/update/:id") { req, res ->
            dao.books.update(
                id = req.id(),
                title = req.qp("title"),
                year = req.qp("year"),
                authorId = req.qp("authorId").toInt()
            )
            "ok"
        }

        delete("/delete/:id") { req, res ->
            dao.books.delete(id = req.id())
            "ok"
        }
    }

    path("/authors") {
        get("") { req, res ->
            DAO.gson.toJson(dao.authors.data)
        }

        get("/:id") { req, res ->
            DAO.gson.toJson(dao.authors.findById(req.id()))
        }

        post("/create") { req, res ->
            dao.authors.save(
                firstName = req.qp("firstName"),
                lastName = req.qp("lastName")
            )
            res.status(201)
            "ok"
        }

        patch("/update/:id") { req, res ->
            dao.authors.update(
                id = req.id(),
                firstName = req.qp("firstName"),
                lastName = req.qp("lastName")
            )
            "ok"
        }

        delete("/delete/:id") { req, res ->
            dao.authors.delete(id = req.id())
            "ok"
        }
    }

}

// REF: https://www.javachannel.org/posts/getting-a-usable-non-localhost-ip-address-for-a-jvm/
fun getAddress(): String {
    return NetworkInterface.getNetworkInterfaces()
        .toList().flatMap {
            it.inetAddresses
                .toList()
                .filter { it.address.size == 4 }
                .filter { !it.isLoopbackAddress }
                .filter { it.address[0] != 10.toByte() }
                .map { it.hostAddress }
        }.first()
}

fun Request.qp(key: String): String = this.queryParams(key)
fun Request.id(): Int = this.params("id").toInt()
fun Request.full(): Boolean = (this.queryParams("full") == "true")