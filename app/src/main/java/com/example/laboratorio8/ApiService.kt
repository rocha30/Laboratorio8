package com.example.laboratorio8

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

// ApiService.kt
interface ApiService {
    // Endpoint para obtener las categorías de la API
    @GET("json/v1/1/categories.php")
    suspend fun getCategories(): CategoryResponse
    @GET("json/v1/1/lookup.php")
    suspend fun getRecipeDetails(@Query("i") recipeId: String): RecipeDetailResponse

}

// Clase que representa la respuesta de categorías
data class CategoryResponse(val categories: List<Category>)
data class RecipeDetailResponse(val meals: List<Recipe>)

