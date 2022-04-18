package com.skiddie.dadsbakery.model.reponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wrapper<T> {
    @Expose
    @SerializedName("meta")
    var meta: com.skiddie.dadsbakery.model.reponse.Meta?= null
    @Expose
    @SerializedName("data")
    var data: T? = null
}