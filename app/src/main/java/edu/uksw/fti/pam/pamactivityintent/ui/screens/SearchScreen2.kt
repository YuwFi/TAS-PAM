package edu.uksw.fti.pam.pamactivityintent.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import edu.uksw.fti.pam.pamactivityintent.Model.Object.SearchModel
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.Model.View.GenreViewModel
import edu.uksw.fti.pam.pamactivityintent.Model.View.SearchViewModel
import edu.uksw.fti.pam.pamactivityintent.Model.View.SortByViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

val Poppins = FontFamily(
    Font(R.font.poppinsbold, FontWeight.Bold),
    Font(R.font.poppinssemibold, FontWeight.SemiBold),
    Font(R.font.poppinsmedium, FontWeight.Medium)
)

private val sortByVm = SortByViewModel()
private val genreVm = GenreViewModel()
private val searchVm = SearchViewModel()


@Composable
fun SearchScreen2() {
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit, block = {
        sortByVm.getSortByList()
    })
    LaunchedEffect(Unit, block = {
        genreVm.getGenreList()
    })
    LaunchedEffect(Unit, block = {
        searchVm.getSearchList()
    })
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .verticalScroll(state = scrollState)
            .background(Color.White)
            .padding(bottom = 60.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = "" ,
                onValueChange = {},
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = Color(0xffFF6740),
                    focusedBorderColor = Color(0xffFF6740),
                    unfocusedBorderColor = Color(0xffE6E6E6),
                    backgroundColor = Color(0xffFFFFF),
                ),
                label = { Text(
                    text = stringResource(R.string.text_advancedsearch),
                    fontFamily = Poppins) },

                )
//            Button(
//                onClick = {},
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
//                shape = RoundedCornerShape(7.dp),
//            ) {
//                Text(
//                    text = stringResource(R.string.text_advancedsearch),
//                    fontFamily = Poppins,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 20.sp,
//                    color = Color.Black,
//                )
//                Icon(
//                    Icons.Default.Search,
//                    contentDescription = "Search",
//                    tint = Color.Black,
//                    modifier = Modifier.size(25.dp)
//                )
//            }
            Image(
                painter = painterResource(id = R.drawable.im_profile_screen_kucing),
                contentDescription = "Profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }

        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember { mutableStateOf("") }
        var textfieldSize by remember { mutableStateOf(Size.Zero) }
        val icon = if (expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(Color.White)
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                label = {
                    Text(
                        text = stringResource(R.string.text_sortby),
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.Black,
                    )
                },
                trailingIcon = {
                    Icon(
                        icon, "sortlist",
                        Modifier.clickable { expanded = !expanded },
                        tint = Color.Black
                    )
                }
            )
            if(sortByVm.errorMessage.isEmpty()){
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    sortByVm.sortByList.forEach(){ label->
                        DropdownMenuItem(onClick = {
                            selectedText = label.sort
                            expanded = false
                        }) {
                            Text(
                                text = label.sort,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            else{
                Column() {
                    Text(text = sortByVm.errorMessage)
                    Text(text = "error woy")
                }

            }

        }

        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            if(genreVm.errorMessage.isEmpty()){
                LazyRow() {
                    items(genreVm.genreList.size) { index ->
                        cardGenre(genreName = genreVm.genreList[index].genre)
                    }
                }
            }

            else{
                Column() {
                    Text(text = genreVm.errorMessage)
                    Text(text = "error woy")
                }
            }


        }
        Text(
            text = stringResource(R.string.text_browse),
            fontFamily = Poppins,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 10.dp),
            color = Color.Black
        )
        if(searchVm.errorMessage.isEmpty()){
            LazyColumn(
                Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(searchVm.searchList.size) { index ->
                    browseCard(searchVm.searchList[index])
                }
            }
        }
        else{
            Column() {
                Text(text = searchVm.errorMessage)
                Text(text = "error woy")
            }


        }

        
    }
}

@Composable
fun cardGenre(genreName: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(26.dp)
            .width(66.dp),
        shape = RoundedCornerShape(7.dp) ,
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = genreName, fontSize = 12.sp, fontFamily = Poppins,
                color = Color.Black)
        }
    }
}

@Composable
fun browseCard(data: SearchModel) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 5.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = data.imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = data.title,
                    fontFamily = Poppins,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .fillMaxWidth(),
                    color = Color.Black,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box() {
                        Row() {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = "Rate",
                                tint = Color.Black,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = data.rate,
                                fontFamily = Poppins,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        }
                    }
                    AsyncImage(
                        model = data.imgFlagUrl,
                        contentDescription = "Country",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(15.dp)
                            .width(26.dp)
                    )
                    Card(
                        modifier = Modifier
                            .wrapContentSize(),
                        backgroundColor = Color(0xffF0F1F2)
                    ) {
                        Text(
                            text = data.status,
                            fontWeight = FontWeight.Normal,
                            fontFamily = Poppins,
                            fontSize = 8.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(2.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.ThumbUp,
                            contentDescription = "Count",
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = data.count,
                            fontWeight = FontWeight.Normal,
                            fontFamily = Poppins,
                            fontSize = 10.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                }
                Text(
                    text = data.des,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Poppins,
                    fontSize = 10.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview2() {
    PAMActivityIntentTheme {
        SearchScreen2()
    }
}