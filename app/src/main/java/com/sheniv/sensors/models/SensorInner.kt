package com.sheniv.sensors.models

data class SensorInner(
    val name: String,
    val vendor: String,
    val version: Int,
    val type: Int,
    val maximumRange: Float,
    val resolution: Float,
    val power: Float,
    val minDelay: Int,
    val stringType: String
)