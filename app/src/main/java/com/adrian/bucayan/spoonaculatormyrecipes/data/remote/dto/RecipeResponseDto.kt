package com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto

import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.RecipeResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RecipeResponseDto(

    @JsonProperty("results")
    var recipes: List<RecipeDto>? = null,
)

fun RecipeResponseDto.toRecipe(): RecipeResponse {
    return RecipeResponse (
        recipes = recipes?.map { it.toRecipe() },
    )
}
