package com.kaushik.mynewsapp.presentation.head_lines

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaushik.mynewsapp.data.remote.dto.ArticleDto

@Composable
fun HeadLinesScreen(viewModel: HeadLinesViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        state.data?.let { data ->
            LazyColumn(Modifier.fillMaxSize()) {
                items(data.articles) { article ->
                    ArticleListItem(article = article, onItemClick = {
                        Log.d("HeadLinesScreen", " ${article.title}")
                    })
                }
            }
        }

        state.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.Center)

            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}

@Composable
fun ArticleListItem(article: ArticleDto, onItemClick: (ArticleDto) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = article.author
        )
        Text(
            text = article.publishedAt,
            modifier = Modifier.clickable { onItemClick(article) })
    }
}