package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import edu.uksw.fti.pam.pamactivityintent.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.ComicActivity
import edu.uksw.fti.pam.pamactivityintent.ComicScreen
import edu.uksw.fti.pam.pamactivityintent.HalamanChapter
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

val fFirestore = Firebase.firestore

@Composable
fun HalamanChapterScreen(judul:String) {
    val lContext = LocalContext.current
    var notFOundby by remember { mutableStateOf(true) }
    var nama by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var create by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var img by remember { mutableStateOf("") }

    val docRef = fFirestore.collection("manga").document(judul)
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                try {
                    nama = document.data!!["judul"].toString()
                    author = document.data!!["author"].toString()
                    genre = document.data!!["genre"].toString()
                    create = document.data!!["create"].toString()
                    img= document.data!!["img"].toString()
                }catch (e :NullPointerException){
                    Toast.makeText(lContext, "No such document",
                        Toast.LENGTH_SHORT).show()
                    notFOundby = false
                }

            } else {
                Log.d(TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
        Box() {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.banner),
                contentDescription = null)
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                if (notFOundby == true){
                    Text(text = "$genre",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
                            .offset(y = -3.dp)
                    )
                    Text(text = "$nama",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
                            .offset(y = -3.dp)
                    )
                    Text(text = "$author",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
                            .offset(y = -3.dp)
                    )
                    Text(text = "Manga",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
                            .offset(y = -3.dp)
                    )
                    Text(text = "Create: $create",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
                            .offset(y = -3.dp)
                    )
                }else{
                    Text(text = "Manga tidak ada",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
                            .offset(y = -3.dp)
                    )
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .height(370.dp)
            .padding(top = 30.dp)
           // .background(MaterialTheme.colorScheme.surface)

        ) {
            if (notFOundby == true){
                LazyColumn () {
                    items(1) { i ->
                        var no:Int
                        no = i+1
                        Card(

                            border = BorderStroke(1.dp,Color.Black),
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize()
                                .height(70.dp)
                                .background(Color.White)
                                .clickable {
                                    val currentuser = FirebaseAuth.getInstance().currentUser!!
                                        .uid
                                    val filename = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date()))
                                    val data = hashMapOf(
                                        "judul" to nama,
                                        "img" to img,
                                        "author" to author,
                                        "date" to filename,
                                        "uid" to currentuser
                                    )
                                    fFirestore.collection("history").document(filename)
                                        .set(data)
                                        .addOnSuccessListener { documentReference ->
                                            lContext.startActivity(
                                                Intent(lContext, ComicActivity::class.java)
                                                    .apply {
                                                        putExtra("chapter", nama)
                                                    }
                                            )
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(lContext, "Failed add data", Toast.LENGTH_SHORT).show()
                                            Log.w(TAG, "Error writing document", e)
                                        }
                                }
                        ) {


                            Text(text = "CHAPTER $no",
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 10.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun chapter() {
    HalamanChapterScreen("")
}