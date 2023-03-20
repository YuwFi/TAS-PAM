package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class SortByModel(
    @SerializedName("id")
    var id :Int,

    @SerializedName("sort")
    var sort :String,

    )
