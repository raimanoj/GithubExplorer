package com.manojrai.androidgithubexplorer.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repositories(

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("full_name")
    val fullName: String,

    @Expose
    @SerializedName("owner")
    val owner: Owner,

    @Expose
    @SerializedName("description")
    val description: String
) {
    data class Owner(

        @Expose
        @SerializedName("avatar_url")
        val avatarUrl: String
    )
}