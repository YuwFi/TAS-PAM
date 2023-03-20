package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.CommunityModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.GenreModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub2
import kotlinx.coroutines.launch

class CommunityViewModel:ViewModel() {
    private var _communityList = mutableStateListOf<CommunityModel>()

    var errorMessage: String by mutableStateOf("")
    val communityList: List<CommunityModel>
        get() = _communityList

    fun getCommunityList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub2.getClient()
            try {
                _communityList.clear()
                _communityList.addAll(apiClient.getCommunity())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}