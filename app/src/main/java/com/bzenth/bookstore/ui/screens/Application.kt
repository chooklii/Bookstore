package com.bzenth.bookstore.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import com.bzenth.bookstore.db.core.Book
import com.bzenth.bookstore.db.dao.BookRepository
import com.bzenth.bookstore.ui.components.AddBook
import com.bzenth.bookstore.ui.components.BookList
import com.bzenth.bookstore.ui.components.Footer
import com.bzenth.bookstore.ui.core.NavigationElement

@Composable
fun Application(repository: BookRepository) {

    val backStack = remember { mutableStateListOf<NavKey>(BookList) }
    val currentRoute = backStack.last() as NavigationElement

    val saveBook = fun(book: Book): Unit{
        repository.insertBook(book)
        // todo on false display Error Message!
        backStack[backStack.lastIndex] = BookList
    }
    val cancel = fun(): Unit{
        backStack[backStack.lastIndex] = BookList
    }

    Scaffold(
        bottomBar = {
            Footer(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (backStack.last() != route) {
                        backStack.add(route)
                    }
                }
            )
        }
    ) { padding ->

        NavDisplay(
            backStack = backStack,
            onBack = {
                if (backStack.size > 1) {
                    backStack.removeLast()
                }
            },
            entryProvider = entryProvider {

                entry<BookList> {
                    BookOverviewScreen(
                        repository = repository
                    )
                }

                entry<AddBook> {
                    BookForm(null, saveBook, cancel)
                }
            },
            modifier = Modifier.padding(padding)
        )
    }
}