package edu.uksw.fti.pam.pamactivityintent


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import edu.uksw.fti.pam.pamactivityintent.ui.screens.BottomNavigationMainScreenView
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            PAMActivityIntentTheme (darkTheme = darkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val username = getIntent().getStringArrayListExtra("username")?:""
                    BottomNavigationMainScreenView(
                        username as ArrayList<String>,
                        darkTheme = darkTheme,
                        onThemeUpdated = { darkTheme = !darkTheme }
                    )
                }
            }
        }
    }
}
