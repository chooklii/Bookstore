package com.example.bookstore.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bookstore.ui.core.NavigationElement

@Composable
fun Footer(
    currentRoute: NavigationElement,
    onNavigate: (NavigationElement) -> Unit
)  {
    NavigationBar { menuEntries.forEach { route ->

            NavigationBarItem(
                selected = currentRoute == route,
                onClick = {
                    onNavigate(route)
                },
                icon = {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = route.title
                    )
                },
                label = {
                    Text(route.title)
                }
            )
        }
    }
}