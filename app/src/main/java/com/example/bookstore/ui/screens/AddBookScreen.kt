package com.example.bookstore.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bookstore.ui.core.Book

@Composable
fun AddBookScreen(onBookAdded: (Book) -> Unit) {
    var name by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(5) }
    var comment by remember { mutableStateOf("") }
    var learning by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = "Add a book",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Save a title, your rating, and an optional note.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Book name") },
            singleLine = true
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Your rating",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                (1..5).forEach { value ->
                    FilterChip(
                        selected = rating == value,
                        onClick = { rating = value },
                        label = { Text("$value ★") }
                    )
                }
            }
        }

        OutlinedTextField(
            value = learning,
            onValueChange = { learning = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            label = { Text("Comment (optional)") },
            minLines = 3
        )

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            label = { Text("Learning (optional)") },
            minLines = 3
        )

        Button(
            onClick = {
                onBookAdded(
                    Book(
                        name = name.trim(),
                        rating = rating,
                        learning = learning.trim().takeIf { it.isNotEmpty() },
                        comment = comment.trim().takeIf { it.isNotEmpty() }
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank()
        ) {
            Text("Save book")
        }
    }
}
