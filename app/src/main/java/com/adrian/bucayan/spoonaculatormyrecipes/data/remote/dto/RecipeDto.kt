package com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto

import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Recipe
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RecipeDto(

    @JsonProperty("password")
    var password: String? = "",

    @JsonProperty("aggregateLikes")
    var aggregateLikes: Int? = null,

   /* @JsonProperty("analyzedInstructions")
    var analyzedInstructions: List<AnalyzedInstruction?>? = null,*/

    @JsonProperty("cheap")
    var cheap: Boolean? = null,

    @JsonProperty("cookingMinutes")
    var cookingMinutes: Int? = null,

    @JsonProperty("creditsText")
    var creditsText: String? = "",

    @JsonProperty("cuisines")
    var cuisines: List<String?>? = null,

    @JsonProperty("dairyFree")
    var dairyFree: Boolean? = null,

    @JsonProperty("diets")
    var diets: List<String?>? = null,

    @JsonProperty("dishTypes")
    var dishTypes: List<String?>? = null,

    @JsonProperty("extendedIngredients")
    var extendedIngredients: List<ExtendedIngredientDto>?= null,

    @JsonProperty("gaps")
    var gaps: String? = "",

    @JsonProperty("glutenFree")
    var glutenFree: Boolean? = null,

    @JsonProperty("healthScore")
    var healthScore: Int? = null,

    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("image")
    var image: String? = "",

    @JsonProperty("imageType")
    var imageType: String? = "",

    @JsonProperty("instructions")
    var instructions: String? = "",

    @JsonProperty("license")
    var license: String? = "",

    @JsonProperty("lowFodmap")
    var lowFodmap: Boolean? = null,

    @JsonProperty("occasions")
    var occasions: List<String?>? = null,

    @JsonProperty("originalId")
    var originalId: String? = "",

    @JsonProperty("preparationMinutes")
    var preparationMinutes: Int?= null,

    @JsonProperty("pricePerServing")
    var pricePerServing: Double?= null,

    @JsonProperty("readyInMinutes")
    var readyInMinutes: Int?= null,

    @JsonProperty("servings")
    var servings: Int?= null,

    @JsonProperty("sourceName")
    var sourceName: String? = "",

    @JsonProperty("sourceUrl")
    var sourceUrl: String? = "",

    @JsonProperty("spoonacularSourceUrl")
    var spoonacularSourceUrl: String? = "",

    @JsonProperty("summary")
    var summary: String? = "",

    @JsonProperty("sustainable")
    var sustainable: Boolean? = null,

    @JsonProperty("title")
    var title: String? = "",

    @JsonProperty("vegan")
    var vegan: Boolean? = null,

    @JsonProperty("vegetarian")
    var vegetarian: Boolean? = null,

    @JsonProperty("veryHealthy")
    var veryHealthy: Boolean? = null,

    @JsonProperty("veryPopular")
    var veryPopular: Boolean? = null,

    @JsonProperty("weightWatcherSmartPoints")
    var weightWatcherSmartPoints: Int? = null,
)

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        password = password,
        aggregateLikes = aggregateLikes,
        /*analyzedInstructions = analyzedInstructions,*/
        cheap = cheap,
        cookingMinutes = cookingMinutes,
        creditsText = creditsText,
        cuisines = cuisines,
        dairyFree = dairyFree,
        diets = diets,
        dishTypes = dishTypes,
        extendedIngredients = extendedIngredients?.map { it.toExtendedIngredient() },
        gaps = gaps,
        glutenFree = glutenFree,
        healthScore = healthScore,
        id = id,
        image = image,
        imageType = imageType,
        instructions = instructions,
        license = license,
        lowFodmap = lowFodmap,
        occasions = occasions,
        originalId = originalId,
        preparationMinutes = preparationMinutes,
        pricePerServing = pricePerServing,
        readyInMinutes = readyInMinutes,
        servings = servings,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        spoonacularSourceUrl = spoonacularSourceUrl,
        summary = summary,
        sustainable = sustainable,
        title = title,
        vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy,
        veryPopular = veryPopular,
        weightWatcherSmartPoints = weightWatcherSmartPoints,
    )
}



