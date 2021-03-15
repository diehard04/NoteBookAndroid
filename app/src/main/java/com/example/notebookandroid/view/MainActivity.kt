package com.example.notebookandroid.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notebookandroid.R
import com.example.notebookandroid.database.DatabaseHelper
import com.example.notebookandroid.database.model.Note


class MainActivity : AppCompatActivity() {
    private val mAdapter: NotesAdapter? = null
    private val notesList: List<Note> = ArrayList()
    private val coordinatorLayout: CoordinatorLayout? = null
    private val recyclerView: RecyclerView? = null
    private val noNotesView: TextView? = null

    private val db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}