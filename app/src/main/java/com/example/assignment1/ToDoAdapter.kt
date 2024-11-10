package com.example.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private val todos: List<ToDoItem>, private val onClick: (ToDoItem) -> Unit) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDueDate: TextView = view.findViewById(R.id.tvDueDate)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todos[position]

        holder.tvTitle.text = todo.title
        holder.tvDueDate.text = todo.dueDate
        holder.tvStatus.text = if (todo.isCompleted) "Completed" else "Not Completed"
        holder.itemView.setOnClickListener { onClick(todo) }
    }
    override fun getItemCount() = todos.size
}
