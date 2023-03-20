package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("id")
    var id :Int,

    @SerializedName("imgUrl")
    var imgUrl :String,

    @SerializedName("title")
    var title :String,

    @SerializedName("rate")
    var rate :String,

    @SerializedName("imgFlagUrl")
    var imgFlagUrl :String,

    @SerializedName("status")
    var status :String,

    @SerializedName("count")
    var count :String,

    @SerializedName("des")
    var des :String,


    )
