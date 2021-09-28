package com.example.todoapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    // RecyclerView
    var toDoList = arrayListOf<ToDoItemsList>()
    lateinit var   myRV:RecyclerView
    // RecyclerView
    private fun customAlert() {
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
        input.hint="Write your to do list here.."
        // here we set the message of our alert dialog
        dialogBuilder // positive button text and action
            .setPositiveButton("Add", DialogInterface.OnClickListener { dialog, id  ->
                // Here you get get input text from the Edittext
                toDoList.add(ToDoItemsList(input.text.toString(),false))
                myRV.adapter!!.notifyDataSetChanged()

            })
            // negative button text and action
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("New Item")
        // add the Edit Text
        alert.setView(input)
        // show alert dialog
        alert.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var addBT = findViewById<FloatingActionButton>(R.id.addBT)

        // RecyclerView
         myRV = findViewById(R.id.rvMain)

        myRV.adapter = RecyclerViewAdapter(toDoList, this)
        myRV.layoutManager = LinearLayoutManager(this)

        addBT.setOnClickListener {
            customAlert()

        }
        // RecyclerView

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAllItems -> {

               val counter= RecyclerViewAdapter(toDoList,this).deleteItems()
                if(counter!=0){
                myRV.adapter!!.notifyDataSetChanged()
                Toast.makeText(this,  "$counter item(s) deleted", Toast.LENGTH_LONG).show()
                }else{Toast.makeText(this,  "Nothing to delete", Toast.LENGTH_LONG).show()}



                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}