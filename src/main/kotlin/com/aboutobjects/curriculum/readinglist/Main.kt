package com.aboutobjects.curriculum.readinglist

import com.aboutobjects.curriculum.readinglist.dao.DAO
import com.aboutobjects.curriculum.readinglist.model.Author
import com.aboutobjects.curriculum.readinglist.model.Book
import com.aboutobjects.curriculum.readinglist.model.ReadingList
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
            <style type="text/css">
table {
  border-collapse: collapse;
}

table, th, td {
  border: 1px solid black;
}

tr:hover {background-color: #f5f5f5;}
tr:nth-child(even) {background-color: #f2f2f2;}
th {
  background-color: #4CAF50;
  color: white;
}
            </style>

            <h2>Welcome to the Curriculum RESTful Server</h2>
            Serving on <a href="$host/hello">$host</a>
            <ul>
                <li>Reading Lists: ${dao.readingList.data.size}</li>
                <li>Books: ${dao.books.data.size}</li>
                <li>Authors: ${dao.authors.data.size}</li>
            </ul>
            <h3>API</h3>
            <p>The links below are some example requests. Note: The content is being sent pretty-printed, so while the browser will not show it that way, you can View Source to see a better representation.</p>
            <p>By default, child Books and Authors are represented only by their IDs (ex: <id>authorId: 10</id>)</p>
            <p>Some endpoints allow appending <i>?full=true</i> to have the results fully populated.</p>

            <div style="overflow-x:auto;"><table>
                <tr><th>Method</th><th>Endpoint</th><th>Full Support</th><th>Description</th><th>Required Params</th></tr>
                <tr>
                    <td>GET</td>
                    <td><a href="$host/hello">/hello</a></td>
                    <td>NO</td>
                    <td>This page</td>
                    <td>&nbsp;</td>
                </tr>
                <tr><th colspan=5>Reading Lists</th></tr>
                <tr>
                    <td>GET</td>
                    <td><a href="$host/lists">/lists</a></td>
                    <td>YES<br/><a href="$host/lists?full=true">Example</a></td>
                    <td>Retrieve all reading lists</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>GET</td>
                    <td><a href="$host/lists/0">/lists/:id</a></td>
                    <td>YES<br/><a href="$host/lists/0?full=true">Example</a></td>
                    <td>Retrieve a specific reading list</td>
                    <td>ID in url</td>
                </tr>
                <tr>
                    <td>PUT</td>
                    <td>/lists/create</td>
                    <td>YES</td>
                    <td>Create a reading list</td>
                    <td>`title` and `bookIds` array in body.</td>
                </tr>
                <tr>
                    <td>POST</td>
                    <td>/lists/update/:id</td>
                    <td>YES</td>
                    <td>Update an existing reading list</td>
                    <td>ID in url, `title` and `bookIds` array in body.</td>
                </tr>
                <tr>
                    <td>DELETE</td>
                    <td>/lists/delete/:id</td>
                    <td>NO</td>
                    <td>Delete an existing reading list</td>
                    <td>ID in url</td>
                </tr>
                <tr><th colspan=5>Books</th></tr>
                <tr>
                    <td>GET</td>
                    <td><a href="$host/books">/books</a></td>
                    <td>YES<br/><a href="$host/books?full=true">Example</a></td>
                    <td>Retrieve all books</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>GET</td>
                    <td><a href="$host/books/12">/books/:id</a></td>
                    <td>YES<br/><a href="$host/books/12?full=true">Example</a></td>
                    <td>Retrieve a specific book</td>
                    <td>ID in url</td>
                </tr>
                <tr>
                    <td>PUT</td>
                    <td>/books/create</td>
                    <td>YES</td>
                    <td>Create a book</td>
                    <td>`title`, `year` and `authorId` in body.</td>
                </tr>
                <tr>
                    <td>POST</td>
                    <td>/books/update/:id</td>
                    <td>YES</td>
                    <td>Update an existing book</td>
                    <td>ID in url, `title`, `year` and `authorId` in body.</td>
                </tr>
                <tr>
                    <td>DELETE</td>
                    <td>/books/delete/:id</td>
                    <td>NO</td>
                    <td>Delete an existing book</td>
                    <td>ID in url</td>
                </tr>
                <tr><th colspan=5>Authors</th></tr>
                <tr>
                    <td>GET</td>
                    <td><a href="$host/authors">/authors</a></td>
                    <td>NO</td>
                    <td>Retrieve all authors</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>GET</td>
                    <td><a href="$host/authors/13">/authors/:id</a></td>
                    <td>NO</td>
                    <td>Retrieve a specific author</td>
                    <td>ID in url</td>
                </tr>
                <tr>
                    <td>POST</td>
                    <td>/authors/find?firstName=Joe&lastName=Smith</td>
                    <td>NO</td>
                    <td>Retrieve a specific author, or 404 Not Found</td>
                    <td>`firstName` and/or `lastName` in the query parameters</td>
                </tr>
                <tr>
                    <td>PUT</td>
                    <td>/authors/create</td>
                    <td>NO</td>
                    <td>Create a author</td>
                    <td>`firstName` and `lastName` in body.</td>
                </tr>
                <tr>
                    <td>POST</td>
                    <td>/authors/update/:id</td>
                    <td>NO</td>
                    <td>Update an existing author</td>
                    <td>ID in url, `firstName` and `lastName` in body.</td>
                </tr>
                <tr>
                    <td>DELETE</td>
                    <td>/authors/delete/:id</td>
                    <td>NO</td>
                    <td>Delete an existing author</td>
                    <td>ID in url</td>
                </tr>
            </table></div>
        """.trimIndent()
    }

    path("/lists") {
        get("") { req, res ->
            req.jsonOut(
                fullFn = {dao.readingList.complete()},
                elseFn = {dao.readingList.data}
            )
        }

        get("/:id") { req, res ->
            req.jsonOut(
                fullFn = {dao.readingList.complete(req.id())},
                elseFn = {dao.readingList.findById(req.id())}
            )
        }

        put("/create") { req, res ->
            val model = req.model(ReadingList::class.java)
            val list = dao.readingList.save(
                title = model.title,
                bookIds = model.bookIds.orEmpty()
            )
            res.status(201)
            req.jsonOut(
                fullFn = {dao.readingList.complete(list)},
                elseFn = {list}
            )
        }

        post("/update/:id") { req, res ->
            val model = req.model(ReadingList::class.java)
            val list = dao.readingList.update(
                id = req.id(),
                title = model.title,
                bookIds = model.bookIds.orEmpty()
            )
            res.status(201)
            req.jsonOut(
                fullFn = {dao.readingList.complete(list)},
                elseFn = {list}
            )
        }

        delete("/delete/:id") { req, res ->
            dao.readingList.delete(id = req.id())
            "ok"
        }
    }

    path("/books") {
        get("") { req, res ->
            req.jsonOut(
                fullFn = {dao.books.complete()},
                elseFn = {dao.books.data}
            )
        }

        get("/:id") { req, res ->
            req.jsonOut(
                fullFn = {dao.books.complete(req.id())},
                elseFn = {dao.books.findById(req.id())}
            )
        }

        put("/create") { req, res ->
            val model = req.model(Book::class.java)
            val book = dao.books.save(
                title = model.title,
                year = model.year,
                authorId = model.authorId ?: -1
            )
            res.status(201)
            req.jsonOut(
                fullFn = {dao.books.complete(book)},
                elseFn = {book}
            )
        }

        post("/update/:id") { req, res ->
            val model = req.model(Book::class.java)
            val book = dao.books.update(
                id = req.id(),
                title = model.title,
                year = model.year,
                authorId = model.authorId ?: -1
            )
            res.status(201)
            req.jsonOut(
                fullFn = {dao.books.complete(book)},
                elseFn = {book}
            )
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

        post("/find") { req, res ->
            val author = dao.authors.findByName(
                firstName = req.queryParams("firstName"),
                lastName = req.queryParams("lastName")
            )
            when(author) {
                null -> {
                    res.status(404)
                    "<html><body><h2>404 Author Not found</h2></body></html>"
                }
                else -> DAO.gson.toJson(author)
            }
        }

        put("/create") { req, res ->
            val model = req.model(Author::class.java)
            val author = dao.authors.save(
                firstName = model.firstName ?: "",
                lastName = model.lastName ?: ""
            )
            res.status(201)
            DAO.gson.toJson(author)
        }

        post("/update/:id") { req, res ->
            val model = req.model(Author::class.java)
            val author = dao.authors.update(
                id = req.id(),
                firstName = model.firstName ?: "",
                lastName = model.lastName ?: ""
            )
            res.status(201)
            DAO.gson.toJson(author)
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

fun <T> Request.model(clz: Class<T>): T = DAO.gson.fromJson(this.body(), clz)
fun Request.id(): Int = this.params("id").toInt()
fun Request.full(): Boolean = (this.queryParams("full") == "true")

fun <T> Request.jsonOut(fullFn: ()->T, elseFn: ()->T): String {
    return when {
        this.full() -> DAO.gson.toJson(fullFn.invoke())
        else -> DAO.gson.toJson(elseFn.invoke())
    }
}
