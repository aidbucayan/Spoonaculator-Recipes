package com.adrian.bucayan.spoonaculatormyrecipes.domain.model

import android.os.Parcelable
import com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto.RecipeDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeResponse(
    var recipes: List<Recipe>? = null
): Parcelable