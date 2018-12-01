# ao-android-curriculum-server
RESTful server for ao-android-curriculum

#### WIP

* download [this](https://gist.githubusercontent.com/malachid/42016991438ab9dcd3155c7db72521f3/raw/d16d4dd991f5505be43ebef4bc7b8f3b6a50dc6a/BooksAndAuthors-v2.json) and save it in your ao-android-curriculum-server directory as BooksAndAuthors.json
* `mvn package`
* `java -jar target/ao-android-curriculum-server-1.0-SNAPSHOT-jar-with-dependencies.jar`
* Open [http://localhost:4567/hello](http://localhost:4567/hello)

### Examples

#### lists

`curl http://localhost:4567/lists`

```json
{
  "0": {
    "title": "My Summer Reading",
    "bookIds": [
      0,
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17
    ],
    "id": 0
  }
}
```

#### books

`curl http://localhost:4567/books`

```json
{
  "0": {
    "title": "For Whom the Bell Tolls",
    "authorId": 0,
    "year": "1951",
    "id": 0
  },
  "1": {
    "title": "War of the Worlds",
    "authorId": 1,
    "year": "1898",
    "id": 1
  },
  "2": {
    "title": "The Time Machine",
    "authorId": 1,
    "year": "1897",
    "id": 2
  },
  "3": {
    "title": "Alice in Wonderland",
    "authorId": 2,
    "year": "1865",
    "id": 3
  },
  "4": {
    "title": "Nature",
    "authorId": 3,
    "year": "1836",
    "id": 4
  },
  "5": {
    "title": "Macbeth",
    "authorId": 4,
    "year": "1603",
    "id": 5
  },
  "6": {
    "title": "Winnie The Pooh",
    "authorId": 5,
    "year": "1926",
    "id": 6
  },
  "7": {
    "title": "Adventures Of Huckleberry Finn",
    "authorId": 6,
    "year": "1884",
    "id": 7
  },
  "8": {
    "title": "War And Peace",
    "authorId": 7,
    "year": "1869",
    "id": 8
  },
  "9": {
    "title": "Sense And Sensibility",
    "authorId": 8,
    "year": "1811",
    "id": 9
  },
  "10": {
    "title": "Emma",
    "authorId": 8,
    "year": "1816",
    "id": 10
  },
  "11": {
    "title": "The Brothers Karamozov",
    "authorId": 9,
    "year": "1880",
    "id": 11
  },
  "12": {
    "title": "Principia",
    "authorId": 10,
    "year": "1687",
    "id": 12
  },
  "13": {
    "title": "Leaves of Grass",
    "authorId": 11,
    "year": "1855",
    "id": 13
  },
  "14": {
    "title": "Utopia",
    "authorId": 12,
    "year": "1516",
    "id": 14
  },
  "15": {
    "title": "Novum Organum",
    "authorId": 13,
    "year": "1620",
    "id": 15
  },
  "16": {
    "title": "Henry V",
    "authorId": 4,
    "year": "1599",
    "id": 16
  },
  "17": {
    "title": "The Mill on the Floss",
    "authorId": 14,
    "year": "1860",
    "id": 17
  }
}

```

#### authors

`curl http://localhost:4567/authors`

```json
{
  "0": {
    "firstName": "Ernest",
    "lastName": "Hemingway",
    "id": 0
  },
  "1": {
    "firstName": "H. G.",
    "lastName": "Wells",
    "id": 1
  },
  "2": {
    "firstName": "Lewis",
    "lastName": "Carroll",
    "id": 2
  },
  "3": {
    "firstName": "Ralph Waldo",
    "lastName": "Emerson",
    "id": 3
  },
  "4": {
    "firstName": "William",
    "lastName": "Shakespeare",
    "id": 4
  },
  "5": {
    "firstName": "A. A.",
    "lastName": "Milne",
    "id": 5
  },
  "6": {
    "firstName": "Mark",
    "lastName": "Twain",
    "id": 6
  },
  "7": {
    "firstName": "Leo",
    "lastName": "Tolstoy",
    "id": 7
  },
  "8": {
    "firstName": "Jane",
    "lastName": "Austen",
    "id": 8
  },
  "9": {
    "firstName": "Fyodor",
    "lastName": "Dostoevsky",
    "id": 9
  },
  "10": {
    "firstName": "Sir Isaac",
    "lastName": "Newton",
    "id": 10
  },
  "11": {
    "firstName": "Walt",
    "lastName": "Whitman",
    "id": 11
  },
  "12": {
    "firstName": "Sir Thomas",
    "lastName": "More",
    "id": 12
  },
  "13": {
    "firstName": "Sir Francis",
    "lastName": "Bacon",
    "id": 13
  },
  "14": {
    "firstName": "George",
    "lastName": "Eliot",
    "id": 14
  }
}

```

#### authors/13

`curl http://localhost:4567/authors/13`

```json
{
  "firstName": "Sir Francis",
  "lastName": "Bacon",
  "id": 13
}

```

#### books/12

`curl http://localhost:4567/books/12`

```json
{
  "title": "Principia",
  "authorId": 10,
  "year": "1687",
  "id": 12
}

```

#### delete/13

`http delete http://localhost:4567/authors/delete/13`

```
HTTP/1.1 200 OK
Content-Type: text/html;charset=utf-8
Date: Sat, 01 Dec 2018 01:20:00 GMT
Server: Jetty(9.3.z-SNAPSHOT)
Transfer-Encoding: chunked

ok

```

`curl http://localhost:4567/authors/13`

```json
null
```

#### authors/create

Currently not working.

```
[qtp35313097-34] INFO spark.http.matching.MatcherFilter - The requested route [/authors/create] has not been mapped in Spark for Accept: [application/json, */*]
```
