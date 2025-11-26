package com.kaushik.mynewsapp.presentation.head_lines

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kaushik.mynewsapp.data.remote.dto.ArticleDto

@Composable
fun HeadLinesScreen(
    viewModel: HeadLinesViewModel = hiltViewModel(),
    onNewsClick: (url: String) -> Unit
) {

    val state = viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {

        state.value.data?.let { data ->
            LazyColumn(Modifier.fillMaxSize()) {
                items(data.articles, key = { it.content }) { article ->
                    ArticleListItem(article = article, onItemClick = {
                        onNewsClick(article.url)
                    })
                }
            }
        }

        state.value.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (state.value.isLoading) {
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
            .clickable { onItemClick(article) }
    ) {
        Column {

            article.urlToImage?.let { imageUrl ->

                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = article.title, style = MaterialTheme.typography.bodyLarge
                )
            }

        }
    }
}