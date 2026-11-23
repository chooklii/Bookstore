package com.example.bookstore.ui.core

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

interface NavigationElement: NavKey{
    val title: String
    val icon: ImageVector // Image Displayed in Menu for this Entry
}