package edu.uksw.fti.pam.pamactivityintent


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.screens.LoginForm
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    val fFirestore = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            PAMActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginForm(onSignInAction = ::doAuth)
                }
            }
        }
    }
    private fun doAuth(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {

                    val currentuser = FirebaseAuth.getInstance().currentUser!!
                        .uid
                    val arrayList = ArrayList<String>()//Creating an empty arraylist
                    arrayList.add(currentuser)//Adding object in arraylist
                    arrayList.add(username)
                    startActivity(
                        Intent(this, MenuActivity::class.java).apply {
                            putStringArrayListExtra("username", arrayList)
                        }
                    )

                    finish()
                } else {
                    Toast.makeText(applicationContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
