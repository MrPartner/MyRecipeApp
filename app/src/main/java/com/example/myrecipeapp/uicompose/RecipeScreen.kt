package com.example.myrecipeapp.uicompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.data.CategoryModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewState: MainViewModel.RecipeState,
    navigateToDetail: (CategoryModel) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text("ERROR OCCURRED")
            }

            else -> {
                CategoryScreen(categories = viewState.list, navigateToDetail)
            }
        }

    }
}


@Composable
fun CategoryScreen(categories: List<CategoryModel>, navigateToDetail: (CategoryModel) -> Unit) {
    Scaffold{innerPadding ->
        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(categories) { category ->
                CategoryItem(category = category, navigateToDetail)
            }
        }
    }
}

//How each Item looks like
@Composable
fun CategoryItem(category: CategoryModel, navigateToDetail: (CategoryModel) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RecipeScreenPreview() {
//    MyRecipeAppTheme {
//        RecipeScreen()
//    }
//}


