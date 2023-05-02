package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.BookmarkModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.ChapModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub2
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub3
import kotlinx.coroutines.launch

class ChapModelView:ViewModel() {
    private var _chapList = mutableStateListOf<ChapModel>()

    var errorMessage: String by mutableStateOf("")
    val chapList: List<ChapModel>
        get() = _chapList

    fun getChapList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub3.getClient()
            try {
                _chapList.clear()
                _chapList.addAll(apiClient.getChap())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}