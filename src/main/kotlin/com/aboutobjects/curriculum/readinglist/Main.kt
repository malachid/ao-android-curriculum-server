package com.aboutobjects.curriculum.readinglist

import com.aboutobjects.curriculum.readinglist.dao.DAO
import org.slf4j.LoggerFactory
import spark.Request
import spark.Spark.*


val dao = DAO()
val logger = LoggerFactory.getLogger("ReadingList")

fun main(args: Array<String>) {

//    port(9091)

    before("/*") { req, res ->
        logger.info("API ${req.url()} ")
    }

    get("/hello") { req, res ->
        """
            <h2>Welcome to the Curriculum RESTful Server</h2>
            Serving on ${req.host()}
            <ul>
                <li>Reading Lists: ${dao.readingList.data.size}</li>
                <li>Books: ${dao.books.data.size}</li>
                <li>Authors: ${dao.authors.data.size}</li>
            </ul>
        """.trimIndent()
    }

    path("/lists") {
        get("") { req, res ->
            DAO.gson.toJson(dao.readingList.data)
        }

        get("/:id") { req, res ->
            DAO.gson.toJson(dao.readingList.findById(req.id()))
        }

        get("/complete/:id") { req, res ->
            DAO.gson.toJson(dao.readingList.complete(req.id()))
        }

        post("/create") { req, res ->
            dao.readingList.save(
                title = req.qp("firstName"),
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
                title = req.qp("firstName"),
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
            DAO.gson.toJson(dao.books.data)
        }

        get("/:id") { req, res ->
            DAO.gson.toJson(dao.books.findById(req.id()))
        }

        get("/complete/:id") { req, res ->
            DAO.gson.toJson(dao.books.complete(req.id()))
        }

        post("/create") { req, res ->
            dao.books.save(
                title = req.qp("firstName"),
                year = req.qp("lastName"),
                authorId = req.qp("authorId").toInt()
            )
            res.status(201)
            "ok"
        }

        patch("/update/:id") { req, res ->
            dao.books.update(
                id = req.id(),
                title = req.qp("firstName"),
                year = req.qp("lastName"),
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

fun Request.qp(key: String): String = this.queryParams(key)
fun Request.id(): Int = this.params("id").toInt()