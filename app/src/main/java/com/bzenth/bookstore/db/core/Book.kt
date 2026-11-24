package com.bzenth.bookstore.db.core

data class Book(
    val rowId: Int,
    val name: String,
    val rating: Int,
    val learning: String?,
    val comment: String?
)

