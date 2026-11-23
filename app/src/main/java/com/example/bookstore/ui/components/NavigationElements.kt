package com.example.bookstore.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Add
import com.example.bookstore.ui.core.NavigationElement
import kotlinx.serialization.Serializable

val menuEntries: List<NavigationElement> = listOf(BookList, AddBook)

@Serializable
data object BookList : NavigationElement {
    override val title = "Meine Bücher"
    override val icon = Icons.AutoMirrored.Filled.LibraryBooks
}

@Serializable
data object AddBook : NavigationElement {
    override val title = "Buch hinzufügen"
    override val icon = Icons.Default.Add
}
