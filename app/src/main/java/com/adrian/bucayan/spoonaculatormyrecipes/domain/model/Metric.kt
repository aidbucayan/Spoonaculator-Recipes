package com.adrian.bucayan.spoonaculatormyrecipes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Metric(
    var amount: Double? = null,
    var unitLong: String? = "",
    var unitShort: String? = "",
): Parcelable