package com.example.dbtest2.DBHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dbtest2.Model.Person

class DBHelper (context: Context) :SQLiteOpenHelper(context,DATABASE_NAME, null, DATABADE_VER){

    companion object {
        private val DATABADE_VER = 1
        private  val DATABASE_NAME = "SAMPLEKOTLIN.db"

        //Table
        private val TABLE_NAME = "Person"
        private val COOL_ID = "Id"
        private val COOL_NAME = "Name"
        private val COLL_EMAIL = "Email"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COOL_ID INTEGER PRIMARY KEY,$COOL_NAME TEXT,$COLL_EMAIL TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD
    val allPerson:List<Person>
        @SuppressLint("Range")
        get(){
            val lstPersons = ArrayList<Person>()
            val selectQueryHandler =  "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQueryHandler,null)
            if (cursor.moveToFirst())
            {
                do {
                    val person = Person()
                    person.id = cursor.getInt(cursor.getColumnIndex(COOL_ID))
                    person.name = cursor.getString(cursor.getColumnIndex(COOL_NAME))
                    person.email = cursor.getString(cursor.getColumnIndex(COLL_EMAIL))

                    lstPersons.add(person)
                }while (cursor.moveToNext())

            }
            db.close()
            return lstPersons

        }

    fun addPerson(person: Person)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COOL_ID, person.id)
        values.put(COOL_NAME, person.name)
        values.put(COLL_EMAIL, person.email)

        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun updatePerson(person: Person):Int
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COOL_ID, person.id)
        values.put(COOL_NAME, person.name)
        values.put(COLL_EMAIL, person.email)

        return db.update(TABLE_NAME,values,"$COOL_ID=?", arrayOf(person.id.toString()))

    }

    fun deletePerson(person: Person)
    {
        val db = this.writableDatabase


        db.delete(TABLE_NAME, "$COOL_ID=?", arrayOf(person.id.toString()))
        db.close()

    }




}