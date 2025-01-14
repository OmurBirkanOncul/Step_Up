package com.example.stepup.models

data class Task(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val iconId: Int = 23,
    val completed: Boolean = false,
    val skipped: Boolean = false,
    val deleted: Boolean = false
)