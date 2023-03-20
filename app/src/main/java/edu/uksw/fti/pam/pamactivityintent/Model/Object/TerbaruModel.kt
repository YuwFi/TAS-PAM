package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class TerbaruModel(
    @SerializedName("id")
    var id :Int,

    @SerializedName("title")
    var title :String,

    @SerializedName("imgUrl")
    var imgUrl :String,

    @SerializedName("des")
    var des :String,

    @SerializedName("author")
    var author :String,

    )
