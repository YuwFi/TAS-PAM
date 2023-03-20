package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.BaruTambahModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.PopularModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub
import kotlinx.coroutines.launch

class BaruViewModel:ViewModel() {
    private var _baruList = mutableStateListOf<BaruTambahModel>()

    var errorMessage: String by mutableStateOf("")
    val baruList: List<BaruTambahModel>
        get() = _baruList

    fun getBaruList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub.getClient()
            try {
                _baruList.clear()
                _baruList.addAll(apiClient.getBaruTambah())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}