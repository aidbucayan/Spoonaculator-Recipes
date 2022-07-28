package com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto

import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Measures
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MeasuresDto(

    @JsonProperty("metric")
    var metric: MetricDto? = null,

    @JsonProperty("us")
    var us: UsDto? = null,
)

fun MeasuresDto.toMeasures(): Measures {
    return Measures (
        metric = metric?.toMetric(),
    )
}
