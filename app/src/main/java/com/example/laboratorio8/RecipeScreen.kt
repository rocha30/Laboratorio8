package com.example.laboratorio8

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun RecipeScreen(categories: List<Category>, onCategoryClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(categories) { category ->
            CategoryCard(category = category, onCategoryClick = onCategoryClick)
        }
    }
}

@Composable
fun CategoryCard(category: Category, onCategoryClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onCategoryClick(category.strCategory) }, // Manejar clics
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberImagePainter(category.strCategoryThumb),
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.strCategory,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Description: ${category.strCategoryDescription}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


// Función para simular la obtención de recetas (puedes reemplazarla con tu lógica)
fun getRecipesForCategory(category: String): List<Recipe> {
    return listOf(
        Recipe("Recipe 1 for $category", "https://www.example.com/image1.jpg", "Delicious dish 1"),
        Recipe("Recipe 2 for $category", "https://www.example.com/image2.jpg", "Delicious dish 2"),
        Recipe("Recipe 3 for $category", "https://www.example.com/image3.jpg", "Delicious dish 3")
    )
}


data class Recipe(
    val name: String,
    val imageUrl: String,
    val description: String?
)

@Composable
fun RecipeCard(recipe: Recipe, isRecipe: Boolean = true) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { /* Manejar clics y navegación */ },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Imagen de la receta o categoría
            Image(
                painter = rememberImagePainter(recipe.imageUrl), // Asegúrate de tener una propiedad de imagen en Recipe
                contentDescription = recipe.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Nombre
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Mostrar la descripción solo si es una receta
            if (isRecipe) {
                Text(
                    text = recipe.description ?: "No description available",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun RecipeListScreen(category: String) {
    val recipes = remember { getRecipesForCategory(category) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(recipes) { recipe ->
            RecipeCard(recipe = recipe)
        }
    }

    fun getRecipesForCategory(categoryId: String): List<Recipe> {

        return listOf(
            Recipe(
                "Recipe 1 for $categoryId",
                "https://www.example.com/image1.jpg",
                "Delicious dish 1"
            ),
            Recipe(
                "Recipe 2 for $categoryId",
                "https://www.example.com/image2.jpg",
                "Delicious dish 2"
            ),
            Recipe(
                "Recipe 3 for $categoryId",
                "https://www.example.com/image3.jpg",
                "Delicious dish 3"
            )
        )
    }
}




