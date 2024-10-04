package com.example.laboratorio8

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService) : ViewModel() {

    private val _categoriesState = MutableStateFlow<List<Category>>(emptyList())
    val categoriesState: StateFlow<List<Category>> = _categoriesState

    private val _recipesState = MutableStateFlow<List<Recipe>>(emptyList())
    val recipesState: StateFlow<List<Recipe>> = _recipesState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categoriesState.value = response.categories
            } catch (e: Exception) {
                _categoriesState.value = emptyList()
            }
        }
    }

    fun fetchRecipesByCategory(category: String, recipeId: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getRecipeDetails(recipeId)
                _recipesState.value = response.meals
            } catch (e: Exception) {
                _recipesState.value = emptyList()
            }
        }
    }
}



