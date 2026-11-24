package com.bzenth.bookstore.ui.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bzenth.bookstore.R
import com.bzenth.bookstore.db.core.Book

@Composable
fun BookForm(book: Book?, onSave: (Book) -> Unit) {
    var name by remember { mutableStateOf(book?.name.orEmpty()) }
    var rating by remember { mutableIntStateOf(book?.rating?.or(5) ?: 5) }
    var comment by remember { mutableStateOf(book?.comment.orEmpty()) }
    var learning by remember { mutableStateOf(book?.learning.orEmpty()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = stringResource(if (book == null) R.string.add_book_title else R.string.edit_book_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.add_book_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.book_name)) },
            singleLine = true
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(R.string.your_rating),
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
            label = { Text(stringResource(R.string.learning_optional)) },
            minLines = 3
        )

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            label = { Text(stringResource(R.string.comment_optional)) },
            minLines = 3
        )

        Button(
            onClick = {
                onSave(
                    Book(
                        rowId = book?.rowId?.or(0) ?: 0,
                        name = name.trim(),
                        rating = rating,
                        learning = comment.trim().takeIf { it.isNotEmpty() },
                        comment = learning.trim().takeIf { it.isNotEmpty() }
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank()
        ) {
            Text(stringResource(R.string.save_book))
        }
    }
}