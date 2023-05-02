package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.Model.Object.GenreModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.MangaModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub2
import kotlinx.coroutines.launch


class MangaViewModel : ViewModel() {
    private var _mangaList = mutableStateListOf<MangaModel>()

    var errorMessage: String by mutableStateOf("")
    val mangaList: List<MangaModel>
    get() = _mangaList

    fun getMangaList(uid:String){
        viewModelScope.launch {

            try {
                val db = Firebase.firestore
                db.collection("history")
                    .whereEqualTo("uid",uid)
                    .get()
                    .addOnSuccessListener { documents ->
                    _mangaList.clear()
                    for (document in documents) {
                        val jd = document["judul"].toString()
                        val img = document["img"].toString()
                        val au = document["author"].toString()
                       _mangaList.add(MangaModel(img,jd,au))
                    }
                }
            }
            catch (e : Exception){
                errorMessage = "NULL"
            }
        }
    }
}