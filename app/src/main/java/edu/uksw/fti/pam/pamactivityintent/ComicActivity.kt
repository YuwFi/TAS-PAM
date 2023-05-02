package edu.uksw.fti.pam.pamactivityintent


import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import edu.uksw.fti.pam.pamactivityintent.Model.View.ChapModelView



import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

class ComicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAMActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val chap = getIntent().getStringExtra("chapter").toString()
                    ComicScreen(chap)
                }
            }
        }
    }
}

private val chapVm = ChapModelView()
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComicScreen(chap : String) {


    LaunchedEffect(Unit, block = {
        chapVm.getChapList()
    })
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xffFF6740),
                title = {
//                    Icon(
//                        Icons.Default.ArrowBack,
//                        contentDescription = null,
//                        tint = Color.White
//                    )
                    Text(
                        modifier = Modifier
                            .offset(x = 15.dp),
                        text = "CHAPTER 1 $chap",
                        color = Color.White
                    )
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column() {
                val storage = Firebase.storage
                val listRef = storage.reference.child("files/$chap").listAll()


//                listRef.listAll()
//                    .addOnSuccessListener { (items, prefixes) ->
//                        prefixes.forEach { prefix ->
//
//                        }
//
//                        items.forEach { item ->
//
//                        }
//                    }
//                    .addOnFailureListener {
//
//                    }
            }
            if(chapVm.errorMessage.isEmpty()){
                LazyColumn(
                    Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    items(chapVm.chapList.size) { index ->
                        var namechap by remember { mutableStateOf("") }
                        val storage = Firebase.storage
                        val listRef = storage.reference.child("manga/$chap/"+chapVm.chapList[index].id)
                        listRef.downloadUrl.addOnSuccessListener { metadata ->
                            namechap = metadata.toString()

                        }.addOnFailureListener {exception ->
                            Log.d(ContentValues.TAG, "get failed with ", exception)
                            namechap = " eror"

                        }
                        AsyncImage(
                            model = namechap,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .height(500.dp),
                            contentScale = ContentScale.Fit
                        )
//                        Text(
//                            text = namechap,
////                        fontFamily = Poppins,
//                            fontSize = 13.sp,
//                            color = Color.Black,
//                            fontStyle = FontStyle.Italic,
//                        )
                    }
                }
            }
            else{
                Column() {
                    Text(text = chapVm.errorMessage)
                    Text(text = "error woy")
                }


            }
            LazyColumn() {

//                items(
//                    listOf(
//                        R.drawable.jjk1,
//                        R.drawable.jjk2,
//                        R.drawable.jjk3,
//                        R.drawable.jjk4,
//                        R.drawable.jjk5,
//                        R.drawable.jjk6,
//                        R.drawable.jjk7,
//                        R.drawable.jjk8,
//                        R.drawable.jjk9,
//                        R.drawable.jjk10
//                    )
//                ) { image ->
//                    Image(
//                        painter = painterResource(id = image),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .height(500.dp),
//                        contentScale = ContentScale.Fit
//                    )
//                }
            }
//            Row() {
//                Button(
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFF6740)),
//                    onClick = { /*TODO*/ }
//                ) {
//                    Icon(
//                        Icons.Default.ArrowBack,
//                        contentDescription = null,
//                        tint = Color.White
//                    )
//                }
//                Button(
//                    modifier = Modifier
//                        .offset(x=20.dp),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFF6740)),
//                    onClick = { /*TODO*/ }
//                ) {
//                    Icon(
//                        Icons.Default.ArrowForward,
//                        contentDescription = null,
//                        tint = Color.White,
//                    )
//                }
//            }
        }
    }
}

@Preview
@Composable
fun ComicScreenPreview() {
    ComicScreen("")
}