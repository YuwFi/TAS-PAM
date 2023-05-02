package edu.uksw.fti.pam.pamactivityintent.Model.Object

import com.google.gson.annotations.SerializedName

data class MangaModel(



    @SerializedName("img")
    var img :String,

    @SerializedName("judul")
    var judul :String,

    @SerializedName("author")
    var author :String,


)
