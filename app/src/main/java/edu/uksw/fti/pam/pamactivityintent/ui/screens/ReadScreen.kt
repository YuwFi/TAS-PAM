package edu.uksw.fti.pam.pamactivityintent.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import edu.uksw.fti.pam.pamactivityintent.Model.Object.ReadModel
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.Model.View.ReadViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

private val readVm = ReadViewModel()

@Composable
fun ReadScreen(){
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit, block = {
        readVm.getSearchList()
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(state = scrollState)
            .background(Color.White)
            .padding(bottom = 60.dp)
    ) {
        Text(
            text = stringResource(R.string.text_browse),
            fontFamily = Poppins,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 10.dp),
            color = Color.Black
        )
        if(readVm.errorMessage.isEmpty()){
            LazyColumn(
                Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(readVm.readList.size) { index ->
                    ListCardUp(readVm.readList[index])
                }
            }
        }
        else{
            Column() {
                Text(text = readVm.errorMessage)
                Text(text = "error woy")
            }


        }

    }
}

@Composable
fun ListCardUp(data: ReadModel) {
    Row() {
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White
        ) {
            Row() {
                AsyncImage(
                    model = data.imgUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
                Box(modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .padding(1.dp)){
                    Column(modifier = Modifier.padding(5.dp)) {
                        Text(
                            text = data.title,
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
                            text = data.chapter,
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
        ReadScreen()
    }
}