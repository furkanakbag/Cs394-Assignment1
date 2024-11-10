package com.example.assignment1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToDoItem(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: String,
    val isCompleted: Boolean
) : Parcelable
