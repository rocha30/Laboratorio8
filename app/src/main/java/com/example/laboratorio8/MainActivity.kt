package com.example.laboratorio8
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.tensorflow.lite.support.label.Category


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear instancia de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear ApiService
        val apiService = retrofit.create(ApiService::class.java)

        // Crear ViewModel sin una Factory separada
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    return MainViewModel(apiService) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }).get(MainViewModel::class.java)

        // Usar setContent con la función que maneja la UI y la navegación
        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                MainContent(viewModel, navController)
            }
        }
    }
}


@Composable
fun MainContent(viewModel: MainViewModel, navController: NavHostController) {
    val categories by viewModel.categoriesState.collectAsState()

    NavHost(navController, startDestination = "recipeScreen") {
        composable("recipeScreen") {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(category = category) { selectedCategory ->
                        // Navegar a la pantalla de recetas usando el idCategory
                        navController.navigate("recipeList/${category.idCategory}")
                    }
                }
            }
        }
        composable("recipeList/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            RecipeListScreen(category)
        }
    }
}



@Composable
fun Material3App(viewModel: MainViewModel, navController: NavHostController) {
    MaterialTheme {
        Surface {
            val categories by viewModel.categoriesState.collectAsState()
            RecipeScreen(categories = categories) { selectedCategory ->
                // Navegar a la pantalla de recetas al hacer clic en una categoría
                navController.navigate("recipeList/$selectedCategory")
            }
        }
    }
}








