# ao-android-curriculum-server
RESTful server for ao-android-curriculum

### Instructions

* download [this](https://gist.githubusercontent.com/malachid/42016991438ab9dcd3155c7db72521f3/raw/d16d4dd991f5505be43ebef4bc7b8f3b6a50dc6a/BooksAndAuthors-v2.json) and save it in your ao-android-curriculum-server directory as BooksAndAuthors.json
* Right-click on `Main.kt` and choose `Run`
  * Alternatively, you can run it manually from the command-line
    * `mvn package`
    * `./run.sh`
* Open [http://localhost:4567/hello](http://localhost:4567/hello)
  * You'll want to give your actual IP address instead of localhost to the classroom.
    * That page will show your actual IP address.
    * Alternatively, find your local IP address [on a Windows/MAC](https://kb.netgear.com/20878/Finding-your-IP-address-without-using-the-command-prompt)
    * Alternatively, find your local IP address [on Linux](https://stackabuse.com/how-to-get-your-ip-address-on-linux/)
* cURL examples below

Note: If you need to expose the server with a public url:
* Install [ngrok](https://ngrok.com/)
* make sure you have run `ngrok authtoken <YOURAUTHTOKEN>` at least once
* run `ngrok http 4567`
* After connecting to the server endpoint via the tunnel, access the dashboard at [http://localhost:4040](http://localhost:4040)

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

`curl -X DELETE 'http://localhost:4567/authors/delete/13'`

```
ok
```

`curl http://localhost:4567/authors/13`

```
null
```

#### authors/create

`curl -X PUT 'http://localhost:4567/authors/create' --data '{"firstName":"Malachi", "lastName":"de AElfweald"}'`

```json
{
  "firstName": "Malachi",
  "lastName": "de AElfweald",
  "id": 15
}
```

#### authors/find

Assuming you didn't delete him above:

`curl-X POST 'http://localhost:4567/authors/find?lastName=Bacon'`

```json
{
  "firstName": "Sir Francis",
  "lastName": "Bacon",
  "id": 13
}
```

`curl -X POST 'http://localhost:4567/authors/find?lastName=Shakespeare'`

```json
{
  "firstName": "William",
  "lastName": "Shakespeare",
  "id": 4
}

```


`curl -X POST 'localhost:4567/authors/find&lastName=Thomas'`

```html
<html><body><h2>404 Author Not found</h2></body></html>
```


#### books/create

`curl -X PUT 'http://localhost:4567/books/create' --data '{"title":"My Book", "year":"2020", "authorId":15}'`

```json
{
  "title": "My Book",
  "authorId": 15,
  "year": "2020",
  "id": 18
}
```

#### lists/create

`curl -X PUT 'http://localhost:4567/lists/create' --data '{"title":"My Reading List", "bookIds":[1,2,5,10]}'`

```json
{
  "title": "My Reading List",
  "bookIds": [
    1,
    2,
    5,
    10
  ],
  "id": 1
}
```


#### books/0?full=true

`curl http://localhost:4567/books/0?full=true`
```json
{
  "title": "For Whom the Bell Tolls",
  "author": {
    "firstName": "Ernest",
    "lastName": "Hemingway",
    "id": 0
  },
  "year": "1951",
  "id": 0
}
```


#### lists/0?full=true

`curl http://localhost:4567/lists/0?full=true`

```json
{
  "title": "My Summer Reading",
  "books": [
    {
      "title": "For Whom the Bell Tolls",
      "author": {
        "firstName": "Ernest",
        "lastName": "Hemingway",
        "id": 0
      },
      "year": "1951",
      "id": 0
    },
    {
      "title": "War of the Worlds",
      "author": {
        "firstName": "H. G.",
        "lastName": "Wells",
        "id": 1
      },
      "year": "1898",
      "id": 1
    },
    {
      "title": "The Time Machine",
      "author": {
        "firstName": "H. G.",
        "lastName": "Wells",
        "id": 1
      },
      "year": "1897",
      "id": 2
    },
    {
      "title": "Alice in Wonderland",
      "author": {
        "firstName": "Lewis",
        "lastName": "Carroll",
        "id": 2
      },
      "year": "1865",
      "id": 3
    },
    {
      "title": "Nature",
      "author": {
        "firstName": "Ralph Waldo",
        "lastName": "Emerson",
        "id": 3
      },
      "year": "1836",
      "id": 4
    },
    {
      "title": "Macbeth",
      "author": {
        "firstName": "William",
        "lastName": "Shakespeare",
        "id": 4
      },
      "year": "1603",
      "id": 5
    },
    {
      "title": "Winnie The Pooh",
      "author": {
        "firstName": "A. A.",
        "lastName": "Milne",
        "id": 5
      },
      "year": "1926",
      "id": 6
    },
    {
      "title": "Adventures Of Huckleberry Finn",
      "author": {
        "firstName": "Mark",
        "lastName": "Twain",
        "id": 6
      },
      "year": "1884",
      "id": 7
    },
    {
      "title": "War And Peace",
      "author": {
        "firstName": "Leo",
        "lastName": "Tolstoy",
        "id": 7
      },
      "year": "1869",
      "id": 8
    },
    {
      "title": "Sense And Sensibility",
      "author": {
        "firstName": "Jane",
        "lastName": "Austen",
        "id": 8
      },
      "year": "1811",
      "id": 9
    },
    {
      "title": "Emma",
      "author": {
        "firstName": "Jane",
        "lastName": "Austen",
        "id": 8
      },
      "year": "1816",
      "id": 10
    },
    {
      "title": "The Brothers Karamozov",
      "author": {
        "firstName": "Fyodor",
        "lastName": "Dostoevsky",
        "id": 9
      },
      "year": "1880",
      "id": 11
    },
    {
      "title": "Principia",
      "author": {
        "firstName": "Sir Isaac",
        "lastName": "Newton",
        "id": 10
      },
      "year": "1687",
      "id": 12
    },
    {
      "title": "Leaves of Grass",
      "author": {
        "firstName": "Walt",
        "lastName": "Whitman",
        "id": 11
      },
      "year": "1855",
      "id": 13
    },
    {
      "title": "Utopia",
      "author": {
        "firstName": "Sir Thomas",
        "lastName": "More",
        "id": 12
      },
      "year": "1516",
      "id": 14
    },
    {
      "title": "Novum Organum",
      "author": {
        "firstName": "Sir Francis",
        "lastName": "Bacon",
        "id": 13
      },
      "year": "1620",
      "id": 15
    },
    {
      "title": "Henry V",
      "author": {
        "firstName": "William",
        "lastName": "Shakespeare",
        "id": 4
      },
      "year": "1599",
      "id": 16
    },
    {
      "title": "The Mill on the Floss",
      "author": {
        "firstName": "George",
        "lastName": "Eliot",
        "id": 14
      },
      "year": "1860",
      "id": 17
    }
  ],
  "id": 0
}
```
