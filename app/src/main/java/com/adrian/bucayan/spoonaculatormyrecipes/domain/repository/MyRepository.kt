package com.adrian.bucayan.spoonaculatormyrecipes.domain.repository

import com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto.RecipeResponseDto

interface MyRepository {

    suspend fun getRecipeList(number: Int, apiKey: String) : RecipeResponseDto

}