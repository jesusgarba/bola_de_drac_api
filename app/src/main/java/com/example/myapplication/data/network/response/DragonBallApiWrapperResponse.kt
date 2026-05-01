package com.example.myapplication.data.network.response

import com.google.gson.annotations.SerializedName

data class DragonBallApiWrapperResponse(
    @SerializedName ("links") val links : LinksResponse,
    @SerializedName("meta") val meta: MetaResponse,
    @SerializedName ("items") val items : List<ItemsResponse>
)
