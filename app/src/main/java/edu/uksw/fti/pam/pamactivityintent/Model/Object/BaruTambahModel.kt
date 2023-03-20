package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class BaruTambahModel(
    @SerializedName("id")
    var id :Int,

    @SerializedName("imgUrl")
    var imgUrl :String
    )
