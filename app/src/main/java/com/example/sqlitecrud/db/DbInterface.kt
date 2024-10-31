package com.example.sqlitecrud.db

import com.example.sqlitecrud.models.User

interface DbInterface {
    fun addUser(user: User)
    fun showUser():List<User>
}