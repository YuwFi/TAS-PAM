package edu.uksw.fti.pam.pamactivityintent

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.screens.SignUp
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme



class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val fFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            PAMActivityIntentTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SignUp(::sendUsernameBackToLogin)
                }
            }
        }
    }
    private fun sendUsernameBackToLogin(
        fnama: String?,
        lnama: String?,
        username: String?,
        password: String?

    ) {



        auth.createUserWithEmailAndPassword(username!!, password!!)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val result = Intent().putExtra("username", username)

                    val currentuser = FirebaseAuth.getInstance().currentUser!!
                        .uid
                    val data = hashMapOf(
                        "firstname" to fnama,
                        "lastname" to lnama,
                        "email" to username,
                        "uid" to currentuser
                    )
                    fFirestore.collection("mangadexAkun")
                        .document(currentuser)
                        .set(data)
                        .addOnSuccessListener { documentReference ->
                            Toast.makeText(applicationContext, "Added data successfuly", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(applicationContext, "Failed add data", Toast.LENGTH_SHORT).show()
                            Log.w(TAG, "Error writing document", e)
                        }

                    setResult(Activity.RESULT_OK, result)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Error Create User", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
