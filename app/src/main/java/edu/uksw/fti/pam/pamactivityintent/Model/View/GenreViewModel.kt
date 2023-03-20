package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.GenreModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub2
import kotlinx.coroutines.launch

class GenreViewModel:ViewModel() {
    private var _genreList = mutableStateListOf<GenreModel>()

    var errorMessage: String by mutableStateOf("")
    val genreList: List<GenreModel>
        get() = _genreList

    fun getGenreList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub2.getClient()
            try {
                _genreList.clear()
                _genreList.addAll(apiClient.getGenre())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}