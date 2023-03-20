package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.GenreModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.SearchModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub2
import kotlinx.coroutines.launch

class SearchViewModel :ViewModel(){

    private var _searchList = mutableStateListOf<SearchModel>()

    var errorMessage: String by mutableStateOf("")
    val searchList: List<SearchModel>
        get() = _searchList

    fun getSearchList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub2.getClient()
            try {
                _searchList.clear()
                _searchList.addAll(apiClient.getSearch())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}