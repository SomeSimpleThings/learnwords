package com.somethingsimple.model.vo

import com.google.gson.annotations.SerializedName


data class Meaning(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("imageUrl") val imageUrl: String?,
    @field:SerializedName("partOfSpeechCode") val partOfSpeechCode: String?,
    @field:SerializedName("previewUrl") val previewUrl: String?,
    @field:SerializedName("soundUrl") val soundUrl: String?,
    @field:SerializedName("transcription") val transcription: String?,
    @field:SerializedName("translation") val translation: Translation?
)