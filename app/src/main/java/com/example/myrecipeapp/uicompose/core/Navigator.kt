package com.example.myrecipeapp.uicompose.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myrecipeapp.data.CategoryModel
import com.example.myrecipeapp.uicompose.CategoryDetailScreen
import com.example.myrecipeapp.uicompose.MainViewModel
import com.example.myrecipeapp.uicompose.RecipeScreen

sealed class Screen(val route: String) {
    data object RecipeScreen : Screen("recipescreen")
    data object DetailScreen : Screen("detailscreen")
}

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<CategoryModel>("cat")
                    ?: CategoryModel("", "", "", "")
            CategoryDetailScreen(category = category)
        }
    }
}