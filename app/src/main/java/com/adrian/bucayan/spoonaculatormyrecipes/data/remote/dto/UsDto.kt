package com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto

import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.ExtendedIngredient
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Measures
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.RecipeResponse
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Us
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UsDto(

    @JsonProperty("amount")
    var amount: Double? = null,

    @JsonProperty("unitLong")
    var unitLong: String? = "",

    @JsonProperty("unitShort")
    var unitShort: String? = "",
)

fun UsDto.toUs(): Us {
    return Us (
        amount = amount,
        unitLong = unitLong,
        unitShort = unitShort,
    )
}
