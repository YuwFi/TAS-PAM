package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.MusimanModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.PopularModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub
import kotlinx.coroutines.launch

class MusimanViewModel:ViewModel() {
    private var _musimanList = mutableStateListOf<MusimanModel>()

    var errorMessage: String by mutableStateOf("")
    val musimanList: List<MusimanModel>
        get() = _musimanList

    fun getMusimanList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub.getClient()
            try {
                _musimanList.clear()
                _musimanList.addAll(apiClient.getMusiman())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}