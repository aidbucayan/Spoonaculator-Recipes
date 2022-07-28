package com.adrian.bucayan.spoonaculatormyrecipes.data.remote

import com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto.RecipeResponseDto
import retrofit2.http.*

interface MyApi {

    @GET("recipes/random?")
    suspend fun getUsers(@Query("number") query: Int, @Query("apiKey") apiKey: String): RecipeResponseDto

}