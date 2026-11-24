package com.bzenth.bookstore.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bzenth.bookstore.ui.core.NavigationElement

@Composable
fun Footer(
    currentRoute: NavigationElement,
    onNavigate: (NavigationElement) -> Unit
)  {
    NavigationBar { menuEntries.forEach { route ->
            val title = stringResource(route.titleRes)

            NavigationBarItem(
                selected = currentRoute == route,
                onClick = {
                    onNavigate(route)
                },
                icon = {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = title
                    )
                },
                label = {
                    Text(title)
                }
            )
        }
    }
}