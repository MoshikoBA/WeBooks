package com.mba.models

open class User(
    email: String,
    firstName: String,
    lastName: String,
    books: ArrayList<Book> = arrayListOf(),
    friends: ArrayList<Friend> = arrayListOf()
) : UserData(email, firstName, lastName, books, friends)

open class UserData(
    val email: String,
    val firstName: String,
    val lastName: String,
    val books: ArrayList<Book>,
    val friends: ArrayList<Friend>
)

class Friend(
    email: String,
    firstName: String,
    lastName: String,
    books: ArrayList<Book> = arrayListOf(),
    friends: ArrayList<Friend> = arrayListOf(),
    val friendshipDate: Long
) : User(email, firstName, lastName, books, friends)