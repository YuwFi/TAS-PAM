package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class BookmarkModel(
    @SerializedName("id")
    var id :Int,

    @SerializedName("imgUrl")
    var imgUrl :String,

    @SerializedName("title")
    var title :String,
)
