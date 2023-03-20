package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class ReadModel(
    @SerializedName("id")
    var id :Int,

    @SerializedName("imgUrl")
    var imgUrl :String,

    @SerializedName("title")
    var title :String,

    @SerializedName("author")
    var author :String,

    @SerializedName("chapter")
    var chapter :String,

    )
