package com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto

import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Metric
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MetricDto(

    @JsonProperty("amount")
    var amount: Double? = null,

    @JsonProperty("unitLong")
    var unitLong: String? = "",

    @JsonProperty("unitShort")
    var unitShort: String? = "",
)

fun MetricDto.toMetric(): Metric {
    return Metric (
        amount = amount,
        unitLong = unitLong,
        unitShort = unitShort,
    )
}
