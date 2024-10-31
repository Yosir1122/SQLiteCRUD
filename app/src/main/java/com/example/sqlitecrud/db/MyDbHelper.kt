package com.example.sqlitecrud.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlitecrud.models.User

class MyDbHelper(private var context: Context):SQLiteOpenHelper(context, DB_NAME,null, VERSION),DbInterface {
    companion object{
        const val DB_NAME="my_contact"
        const val TABLE_NAME="contact_table"
        const val VERSION=1

        const val ID="id"
        const val NAME="name"
        const val NUMBER="number"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query="create table $TABLE_NAME($ID integer not null primary key autoincrement unique, $NAME text not null, $NUMBER text not null  )"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    override fun addUser(user: User) {
        val database= this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(NAME,user.name)
        contentValues.put(NUMBER,user.number)
        database.insert(TABLE_NAME,null,contentValues)
        database.close()
    }

    override fun showUser():List<User> {
        val list=ArrayList<User>()
        val database=this.readableDatabase
        val query="select * from $TABLE_NAME"
        val cursor=database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val user=User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(user)
            }while (cursor.moveToNext())
        }
        return list
    }
}