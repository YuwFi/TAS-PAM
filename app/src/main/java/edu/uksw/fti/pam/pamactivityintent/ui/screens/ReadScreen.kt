package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import edu.uksw.fti.pam.pamactivityintent.HalamanChapter
import edu.uksw.fti.pam.pamactivityintent.Model.Object.MangaModel
import edu.uksw.fti.pam.pamactivityintent.Model.Object.ReadModel
import edu.uksw.fti.pam.pamactivityintent.Model.View.MangaViewModel
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.Model.View.ReadViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme


private val readVm = ReadViewModel()
private val mangaVm = MangaViewModel()

@Composable
fun ReadScreen(uid:String){

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit, block = {
        readVm.getSearchList()
    })
    LaunchedEffect(Unit, block = {
        mangaVm.getMangaList(uid)
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(state = scrollState)
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 60.dp)
    ) {
        val currentuser = FirebaseAuth.getInstance().currentUser!!
            .uid

        if(mangaVm.errorMessage.isEmpty()){
            LazyColumn(
                Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(mangaVm.mangaList.size) { index ->
                    ListCardUp(mangaVm.mangaList[index])
//                    Text(text = currentuser)
                }
            }
        }
        else{
            Column() {
                Text(text = mangaVm.errorMessage)
                Text(text = "error woy")
            }


        }

    }
}

@Composable
fun ListCardUp(data: MangaModel) {
    val lContext = LocalContext.current
    Row() {
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(5.dp)
                .clickable {
                    lContext.startActivity(
                        Intent(lContext, HalamanChapter::class.java)
                        .apply {
                            putExtra("manga", data.judul)
                        }
                    ) },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White
        ) {
            Row() {
                AsyncImage(
                    model = data.img,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
                Box(modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .padding(1.dp)){
                    Column(modifier = Modifier.padding(5.dp)) {
                        Text(
                            text = data.judul,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .requiredHeight(35.dp)
                        )
                        Text(
                            text = data.author,
                            fontFamily = Poppins,
                            fontSize = 10.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .requiredHeight(25.dp)

                        )
                        Text(
                            text = "#1",
                            fontFamily = Poppins,
                            fontSize = 13.sp,
                            color = Color.Black,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .requiredWidth(40.dp)

                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReadScreenPreview() {
    PAMActivityIntentTheme {
        ReadScreen("")
    }
}