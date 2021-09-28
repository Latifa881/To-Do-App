package com.example.todoapp


import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_row.view.*


class RecyclerViewAdapter(private val toDoList: ArrayList<ToDoItemsList>, var context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemObject = toDoList[position]

        holder.itemView.apply {
            tvItem.text = itemObject.text
            tvItem.setTextColor(Color.BLACK)
            cbItem.isChecked = itemObject.checked

            cbItem.setOnClickListener {
                if (cbItem.isChecked) {
                    tvItem.setTextColor(Color.GRAY)
                    itemObject.checked=true
                } else {
                    tvItem.setTextColor(Color.BLACK)
                   itemObject.checked=false
                }

            }

        }
    }
    fun deleteItems():Int {
        var counter=0
        val iterator = toDoList.iterator()
        while(iterator.hasNext()){
            val item = iterator.next()
            if(item.checked){
                    counter++
                iterator.remove()
            }
        }

        return counter
    }

    override fun getItemCount() = toDoList.size
}