package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class CommunityModel(
    @SerializedName("id")
    var id :Int,

    @SerializedName("imgUrl")
    var imgUrl :String,

    @SerializedName("name")
    var name :String,
)

