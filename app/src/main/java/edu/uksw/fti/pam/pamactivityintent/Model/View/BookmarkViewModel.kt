package edu.uksw.fti.pam.pamactivityintent.Model.View

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.Model.Object.BookmarkModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.ReadModel
import edu.uksw.fti.pam.pamactivityintent.Repositories.JsonGitHub2
import kotlinx.coroutines.launch

class BookmarkViewModel:ViewModel() {
    private var _bookmarkList = mutableStateListOf<BookmarkModel>()

    var errorMessage: String by mutableStateOf("")
    val bookmarkList: List<BookmarkModel>
        get() = _bookmarkList

    fun getBookmarkList(){
        viewModelScope.launch {
            val apiClient = JsonGitHub2.getClient()
            try {
                _bookmarkList.clear()
                _bookmarkList.addAll(apiClient.getBookmark())
            }
            catch (e : Exception){
                errorMessage = e.message!!
            }
        }
    }
}