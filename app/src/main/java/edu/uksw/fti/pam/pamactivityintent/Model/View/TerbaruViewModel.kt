package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.PopularModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.TerbaruModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub
import kotlinx.coroutines.launch

class TerbaruViewModel: ViewModel() {
    private var _terbaruList = mutableStateListOf<TerbaruModel>()

    var errorMessage: String by mutableStateOf("")
    val terbaruList: List<TerbaruModel>
        get() = _terbaruList

    fun getTerbaruList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub.getClient()
            try {
                _terbaruList.clear()
                _terbaruList.addAll(apiClient.getTerbaru())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}