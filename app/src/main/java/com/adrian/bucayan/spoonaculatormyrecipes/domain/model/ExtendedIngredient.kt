package com.adrian.bucayan.spoonaculatormyrecipes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredient(
    var aisle: String? = "",
    var amount: Double? = 0.0,
    var consistency: String? = "",
    var id: Int? = null,
    var image: String? = "",
    var measures: Measures? = null,
    var meta: List<String?>? = null,
    var name: String? = "",
    var nameClean: String? = "",
    var original: String? = "",
    var originalName: String? = "",
    var unit: String? = "",
): Parcelable