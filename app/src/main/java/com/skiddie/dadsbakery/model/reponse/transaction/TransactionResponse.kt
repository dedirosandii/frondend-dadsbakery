package com.skiddie.dadsbakery.model.reponse.transaction


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @Expose
    @SerializedName("current_page")
    val currentPage: Int,
    @Expose
    @SerializedName("data")
    val `data`: List<com.skiddie.dadsbakery.model.reponse.transaction.Data>,
    @Expose
    @SerializedName("first_page_url")
    val firstPageUrl: String,
    @Expose
    @SerializedName("from")
    val from: Any,
    @Expose
    @SerializedName("last_page")
    val lastPage: Int,
    @Expose
    @SerializedName("last_page_url")
    val lastPageUrl: String,
    @Expose
    @SerializedName("links")
    val links: List<com.skiddie.dadsbakery.model.reponse.transaction.Link>,
    @Expose
    @SerializedName("next_page_url")
    val nextPageUrl: Any,
    @Expose
    @SerializedName("path")
    val path: String,
    @Expose
    @SerializedName("per_page")
    val perPage: Int,
    @Expose
    @SerializedName("prev_page_url")
    val prevPageUrl: Any,
    @Expose
    @SerializedName("to")
    val to: Any,
    @Expose
    @SerializedName("total")
    val total: Int
)