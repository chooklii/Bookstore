package com.example.bookstore.ui.components

import com.example.bookstore.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Add
import com.example.bookstore.ui.core.NavigationElement
import kotlinx.serialization.Serializable

val menuEntries: List<NavigationElement> = listOf(BookList, AddBook)

@Serializable
data object BookList : NavigationElement {
    override val titleRes = R.string.menu_mybook
    override val icon = Icons.AutoMirrored.Filled.LibraryBooks
}

@Serializable
data object AddBook : NavigationElement {
    override val titleRes = R.string.menu_add_book
    override val icon = Icons.Default.Add
}
