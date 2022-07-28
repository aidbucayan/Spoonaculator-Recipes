package com.adrian.bucayan.spoonaculatormyrecipes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    var password: String? = "",
    var aggregateLikes: Int? = null,
   /* var analyzedInstructions: List<AnalyzedInstruction?>? = null,*/
    var cheap: Boolean? = null,
    var cookingMinutes: Int? = null,
    var creditsText: String? = "",
    var cuisines: List<String?>? = null,
    var dairyFree: Boolean? = null,
    var diets: List<String?>? = null,
    var dishTypes: List<String?>? = null,
    var extendedIngredients: List<ExtendedIngredient>?= null,
    var gaps: String? = "",
    var glutenFree: Boolean? = null,
    var healthScore: Int? = null,
    var id: Int? = null,
    var image: String? = "",
    var imageType: String? = "",
    var instructions: String? = "",
    var license: String? = "",
    var lowFodmap: Boolean? = null,
    var occasions: List<String?>? = null,
    var originalId: String? = "",
    var preparationMinutes: Int?= null,
    var pricePerServing: Double?= null,
    var readyInMinutes: Int?= null,
    var servings: Int?= null,
    var sourceName: String?,
    var sourceUrl: String?= null,
    var spoonacularSourceUrl: String?= null,
    var summary: String?= null,
    var sustainable: Boolean?= null,
    var title: String?= null,
    var vegan: Boolean?= null,
    var vegetarian: Boolean? = null,
    var veryHealthy: Boolean? = null,
    var veryPopular: Boolean? = null,
    var weightWatcherSmartPoints: Int? = null,
): Parcelable