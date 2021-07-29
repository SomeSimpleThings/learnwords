package com.somethingsimple.learnwords.data.vo


import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("note") val note: String?,
    @SerializedName("text") val text: String?
)