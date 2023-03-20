package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.PopularModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub
import kotlinx.coroutines.launch

class PopularViewModel:ViewModel() {
    private var _popularList = mutableStateListOf<PopularModel>()

    var errorMessage: String by mutableStateOf("")
    val popularList: List<PopularModel>
        get() = _popularList

    fun getPopularList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub.getClient()
            try {
                _popularList.clear()
                _popularList.addAll(apiClient.getPopular())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}