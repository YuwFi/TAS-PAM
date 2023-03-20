package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.ReadModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.SearchModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub2
import kotlinx.coroutines.launch

class ReadViewModel:ViewModel() {
    private var _readList = mutableStateListOf<ReadModel>()

    var errorMessage: String by mutableStateOf("")
    val readList: List<ReadModel>
        get() = _readList

    fun getSearchList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub2.getClient()
            try {
                _readList.clear()
                _readList.addAll(apiClient.getRead())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}