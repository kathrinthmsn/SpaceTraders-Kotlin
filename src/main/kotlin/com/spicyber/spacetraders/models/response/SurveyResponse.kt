package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Cooldown
import com.spicyber.spacetraders.models.Survey
import kotlinx.serialization.Serializable

@Serializable
data class SurveyResponse(
    val data: SurveyData
)

@Serializable
data class SurveyData(
    val cooldown: Cooldown,
    val surveys: List<Survey>
)