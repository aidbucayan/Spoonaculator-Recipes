package com.adrian.bucayan.spoonaculatormyrecipes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Measures(
    var metric: Metric? = null,
    var us: Us?  = null,
): Parcelable