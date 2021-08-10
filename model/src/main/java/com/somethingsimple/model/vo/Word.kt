package com.somethingsimple.model.vo


import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("id") val id: Int?,
    @SerializedName("meanings") val meanings: List<Meaning>?,
    @SerializedName("text") val text: String?
)