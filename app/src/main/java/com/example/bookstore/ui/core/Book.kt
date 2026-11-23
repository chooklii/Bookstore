package com.example.bookstore.ui.core

data class Book(
    val name: String,
    val rating: Int,
    val learning: String? = null,
    val comment: String? = null
)
