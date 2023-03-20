package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class GenreModel(
    @SerializedName("id")
    var id :Int,

    @SerializedName("genre")
    var genre :String,

    )
