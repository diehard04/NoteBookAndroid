package com.example.notebookandroid.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.notebookandroid.database.model.Note


/**
 * Created by DieHard_04 on 15-03-2021.
 */

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        // create notes table
        db.execSQL(Note.CREATE_TABLE)
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME)
        // Create tables again
        onCreate(db)
    }

    fun insertNote(note: String?): Long {

        // get writable database as we want to write data
        val db = this.writableDatabase
        val values = ContentValues()
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Note.COLUMN_NOTE, note)

        // insert row
        val id = db.insert(Note.TABLE_NAME, null, values)

        // close db connection
        db.close()

        // return newly inserted row id
        return id
    }

    fun getNote(id: Long): Note {
        // get readable database as we are not inserting anything
        val db = this.readableDatabase
        val cursor: Cursor? = db.query(Note.TABLE_NAME, arrayOf(Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP),
            Note.COLUMN_ID.toString() + "=?", arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )

        cursor?.moveToFirst()

        // prepare note object
        val note = Note(
            cursor!!.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
            cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
            cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP))
        )

        // close the db connection
        cursor.close()
        return note
    }

    fun getAllNotes(): List<Note> {
        val notes: MutableList<Note> = ArrayList()

        // Select All Query
        val selectQuery = "SELECT  * FROM " + Note.TABLE_NAME.toString() + " ORDER BY " + Note.COLUMN_TIMESTAMP.toString() + " DESC"
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val note = Note()
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)))
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)))
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)))
                notes.add(note)
            } while (cursor.moveToNext())
        }

        // close db connection
        db.close()

        // return notes list
        return notes
    }

    fun getNotesCount(): Int {
        val countQuery = "SELECT  * FROM " + Note.TABLE_NAME
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(countQuery, null)
        val count: Int = cursor.getCount()
        cursor.close()


        // return count
        return count
    }

    fun updateNote(note: Note): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Note.COLUMN_NOTE, note.getNote())

        // updating row
        return db.update(
            Note.TABLE_NAME,
            values,
            Note.COLUMN_ID.toString() + " = ?",
            arrayOf(java.lang.String.valueOf(note.getId()))
        )
    }

    fun deleteNote(note: Note) {
        val db = this.writableDatabase
        db.delete(
            Note.TABLE_NAME,
            Note.COLUMN_ID.toString() + " = ?",
            arrayOf(java.lang.String.valueOf(note.getId()))
        )
        db.close()
    }

    companion object {
        // Database Version
        private const val DATABASE_VERSION = 1
        // Database Name
        private const val DATABASE_NAME = "notes_db"
    }
}