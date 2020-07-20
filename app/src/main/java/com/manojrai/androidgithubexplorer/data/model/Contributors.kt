package com.manojrai.androidgithubexplorer.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Contributors(
    @Expose
    @SerializedName("login")
    val name: String,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String
)