package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.PopularModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.SortByModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub
import kotlinx.coroutines.launch

class SortByViewModel:ViewModel() {
    private var _sortByList = mutableStateListOf<SortByModel>()

    var errorMessage: String by mutableStateOf("")
    val sortByList: List<SortByModel>
        get() = _sortByList

    fun getSortByList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub.getClient()
            try {
                _sortByList.clear()
                _sortByList.addAll(apiClient.getSortBy())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}