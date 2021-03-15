package com.example.notebookandroid.database.model

/**
 * Created by Dipak Kumar Mehta on 15-03-2021.
 */

class Note {
    private var id = 0
    private var note: String? = null
    private var timestamp: String? = null

    constructor() {}
    constructor(id: Int, note: String?, timestamp: String?) {
        this.id = id
        this.note = note
        this.timestamp = timestamp
    }

    fun getId(): Int {
        return id
    }

    fun getNote(): String? {
        return note
    }

    fun setNote(note: String?) {
        this.note = note
    }

    fun getTimestamp(): String? {
        return timestamp
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setTimestamp(timestamp: String?) {
        this.timestamp = timestamp
    }

    companion object {
        const val TABLE_NAME = "notes"
        const val COLUMN_ID = "id"
        const val COLUMN_NOTE = "note"
        const val COLUMN_TIMESTAMP = "timestamp"

        // Create table SQL query
        const val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOTE + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")")
    }
}