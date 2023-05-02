package edu.uksw.fti.pam.pamactivityintent.ui.screens


import android.annotation.SuppressLint
import android.content.Intent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import edu.uksw.fti.pam.pamactivityintent.HalamanChapter
import edu.uksw.fti.pam.pamactivityintent.Model.View.BookmarkViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

private val bookmarVm = BookmarkViewModel()

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RankScreen(){
    val lContext = LocalContext.current
    LaunchedEffect(Unit, block = {
        bookmarVm.getBookmarkList()
    })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 60.dp)
    ) {
        Scaffold() {
            if(bookmarVm.errorMessage.isEmpty()){
                LazyVerticalGrid(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    columns = GridCells.Fixed(2),
                ) {
                    items(bookmarVm.bookmarkList.size) { index->
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(5.dp, bottom = 10.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .clickable {
                                    lContext.startActivity(
                                        Intent(lContext, HalamanChapter::class.java)
                                            .apply {
                                                putExtra("manga", bookmarVm.bookmarkList[index].title)
                                            }
                                    ) }
                        ) {
                            AsyncImage(
                                model = bookmarVm.bookmarkList[index].imgUrl,
                                contentDescription = null ,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.height(280.dp).drawWithCache() {
                                    val gradient = Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black),
                                        startY = size.height/3,
                                        endY = size.height
                                    )
                                    onDrawWithContent {
                                        drawContent()
                                        drawRect(gradient,blendMode = BlendMode.Multiply)
                                    }
                                }
                            )
                            Row (
                                Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .align(Alignment.BottomCenter)
                                    .padding(horizontal = 10.dp),
                                Arrangement.Center
                            ){
                                var no = index+1
                                Text(text = "$no. " +bookmarVm.bookmarkList[index].title,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        color = Color.White
                                    ),
                                    modifier = Modifier
                                        .padding(bottom = 9.dp),
                                )
                            }
                        }

                    }
                }

            }
            else{
                Column() {
                    Text(text = bookmarVm.errorMessage)
                    Text(text = "error woy")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable

fun BookmarkScreenPreview(){
    PAMActivityIntentTheme {
        RankScreen()
    }
}
