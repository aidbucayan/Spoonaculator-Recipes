package com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto

import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.ExtendedIngredient
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Measures
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ExtendedIngredientDto(

    @JsonProperty("aisle")
    var aisle: String? = "",

    @JsonProperty("amount")
    var amount: Double? = null,

    @JsonProperty("consistency")
    var consistency: String? = "",

    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("image")
    var image: String? = "",

    @JsonProperty("measures")
    var measures: Measures? = null,

    @JsonProperty("meta")
    var meta: List<String?>? = null,

    @JsonProperty("name")
    var name: String? = "",

    @JsonProperty("nameClean")
    var nameClean: String? = "",

    @JsonProperty("original")
    var original: String? = "",

    @JsonProperty("originalName")
    var originalName: String? = "",

    @JsonProperty("unit")
    var unit: String? = "",
)

fun ExtendedIngredientDto.toExtendedIngredient(): ExtendedIngredient {
    return ExtendedIngredient (
        aisle = aisle,
        amount = amount,
        consistency = consistency,
        id = id,
        image = image,
        measures = measures,
        meta = meta,
        name = name,
        nameClean = nameClean,
        original = original,
        originalName = originalName,
        unit = unit,
    )
}
