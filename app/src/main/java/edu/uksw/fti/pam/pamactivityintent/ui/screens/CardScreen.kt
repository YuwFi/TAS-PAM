//package edu.uksw.fti.pam.pamactivityintent.ui.screens
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.unit.toSize
//import edu.uksw.fti.pam.pamactivityintent.R
//import edu.uksw.fti.pam.pamactivityintent.models.Toon
//import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
//
//@Composable
//fun screenAction() {
////    val scrollState = rememberScrollState()
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 10.dp)
//    ) {
//        Column(
//            modifier = Modifier
////                .verticalScroll(state = scrollState)
//                .background(Color.White)
//        ) {
//
//            Row(
//                modifier = Modifier
//                    .padding(10.dp)
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Button(
//                    onClick = {},
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
//                    shape = RoundedCornerShape(7.dp),
//                ) {
//                    Text(
//                        text = stringResource(R.string.text_advancedsearch),
//                        fontFamily = Poppins,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 20.sp,
//                        color = Color.Black,
//                    )
//                    Icon(
//                        Icons.Default.Search,
//                        contentDescription = "Search",
//                        tint = Color.Black,
//                        modifier = Modifier.size(25.dp)
//                    )
//                }
//                Image(
//                    painter = painterResource(id = R.drawable.im_profile_screen_kucing),
//                    contentDescription = "Profile picture",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(50.dp)
//                        .clip(CircleShape)
//                )
//            }
//
//            var expanded by remember { mutableStateOf(false) }
//            val suggestions = listOf(
//                stringResource(R.string.text_sort_latestupdate),
//                stringResource(R.string.text_sort_highestranking),
//                stringResource(R.string.text_sort_mostfollows),
//                stringResource(R.string.text_completed)
//            )
//            var selectedText by remember { mutableStateOf("") }
//            var textfieldSize by remember { mutableStateOf(Size.Zero) }
//            val icon = if (expanded)
//                Icons.Filled.KeyboardArrowUp
//            else
//                Icons.Filled.KeyboardArrowDown
//
//            Column(
//                modifier = Modifier
//                    .padding(10.dp)
//            ) {
//                OutlinedTextField(
//                    value = selectedText,
//                    onValueChange = { selectedText = it },
//                    shape = RoundedCornerShape(10.dp),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(55.dp)
//                        .background(Color.White)
//                        .onGloballyPositioned { coordinates ->
//                            textfieldSize = coordinates.size.toSize()
//                        },
//                    label = {
//                        Text(
//                            text = stringResource(R.string.text_sortby),
//                            fontFamily = Poppins,
//                            fontWeight = FontWeight.SemiBold,
//                            fontSize = 14.sp,
//                            color = Color.Black,
//                        )
//                    },
//                    trailingIcon = {
//                        Icon(
//                            icon, "sortlist",
//                            Modifier.clickable { expanded = !expanded },
//                            tint = Color.Black
//                        )
//                    }
//                )
//                DropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false },
//                    modifier = Modifier
//                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
//                ) {
//                    suggestions.forEach { label ->
//                        DropdownMenuItem(onClick = {
//                            selectedText = label
//                            expanded = false
//                        }) {
//                            Text(
//                                text = label,
//                                color = Color.Black
//                            )
//                        }
//                    }
//                }
//            }
//
//            Row(
//                modifier = Modifier
//                    .padding(10.dp)
//            ) {
//                Card(
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .height(26.dp)
//                        .width(66.dp),
//                    shape = RoundedCornerShape(7.dp) ,
//                    elevation = 8.dp,
//                    backgroundColor = Color.Black
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = stringResource(R.string.text_genre_action), fontSize = 12.sp, fontFamily = Poppins,
//                            color = Color.White)
//                    }
//                }
//                Card(
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .height(26.dp)
//                        .width(66.dp),
//                    shape = RoundedCornerShape(7.dp) ,
//                    elevation = 8.dp,
//                    backgroundColor = Color.White
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = stringResource(R.string.text_genre_curse), fontSize = 12.sp, fontFamily = Poppins,
//                            color = Color.Black)
//                    }
//                }
//                Card(
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .height(26.dp)
//                        .width(66.dp),
//                    shape = RoundedCornerShape(7.dp) ,
//                    elevation = 8.dp,
//                    backgroundColor = Color.White
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = stringResource(R.string.text_genre_fantasy), fontSize = 12.sp, fontFamily = Poppins,
//                            color = Color.Black)
//                    }
//                }
//                Card(
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .height(26.dp)
//                        .width(66.dp),
//                    shape = RoundedCornerShape(7.dp) ,
//                    elevation = 8.dp,
//                    backgroundColor = Color.White
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = stringResource(R.string.text_genre_isekai), fontSize = 12.sp, fontFamily = Poppins,
//                            color = Color.Black)
//                    }
//                }
//                Card(
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .height(26.dp)
//                        .width(66.dp),
//                    shape = RoundedCornerShape(7.dp) ,
//                    elevation = 8.dp,
//                    backgroundColor = Color.White
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = stringResource(R.string.text_genre_magic), fontSize = 12.sp, fontFamily = Poppins,
//                            color = Color.Black)
//                    }
//                }
//                Card(
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .height(26.dp)
//                        .width(66.dp),
//                    shape = RoundedCornerShape(7.dp) ,
//                    elevation = 8.dp,
//                    backgroundColor = Color.White
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = stringResource(R.string.text_genre_emperror), fontSize = 12.sp, fontFamily = Poppins,
//                            color = Color.Black)
//                    }
//                }
//            }
//            Text(
//                text = stringResource(R.string.text_browse),
//                fontFamily = Poppins,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.padding(start = 10.dp),
//                color = Color.Black
//            )
//            val searchList = listOf(
//                Toon(
//                    1,
//                    "I'm Not That Kind of Talent",
//                    stringResource(R.string.text_nottalent_desc),
//                    painterResource(R.drawable.imtalented),
//                    "4.7/5",
//                    painterResource(R.drawable.southkr),
//                    stringResource(R.string.text_ongoing),
//                    "10k"
//                ),
//                Toon(
//                    2,
//                    "Lout of Count's Family",
//                    stringResource(R.string.text_countfam_desc),
//                    painterResource(R.drawable.trashcount),
//                    "4.9/5",
//                    painterResource(R.drawable.southkr),
//                    stringResource(R.string.text_completed),
//                    "780k"
//                ),
//                Toon(
//                    3,
//                    "The Novel's Extra",
//                    stringResource(R.string.text_novelextra_desc),
//                    painterResource(R.drawable.novelextra),
//                    "4.2/5",
//                    painterResource(R.drawable.southkr),
//                    stringResource(R.string.text_completed),
//                    "100k"
//                ),
//                Toon(
//                    4,
//                    "Life of Magic Academy Mage",
//                    stringResource(R.string.text_magicacademy_desc),
//                    painterResource(R.drawable.magicacademy),
//                    "4.4/5",
//                    painterResource(R.drawable.southkr),
//                    stringResource(R.string.text_ongoing),
//                    "97k"
//                ),
//                Toon(
//                    5,
//                    "The Frozen Player Returns",
//                    stringResource(R.string.text_frozenplayer_desc),
//                    painterResource(R.drawable.frozenplayer),
//                    "4.6/5",
//                    painterResource(R.drawable.southkr),
//                    stringResource(R.string.text_completed),
//                    "339k"
//                ),
//            )
//            LazyColumn(
//                Modifier.fillMaxWidth(),
//                contentPadding = PaddingValues(16.dp)
//            ) {
//                items(searchList.size) { index ->
//                    actionManhwa(searchList[index])
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun actionManhwa(data: Toon) {
//    Card(
//        modifier = Modifier
//            .height(150.dp)
//            .fillMaxWidth()
//            .padding(5.dp),
//        shape = RoundedCornerShape(8.dp),
//        backgroundColor = Color.White,
//        elevation = 5.dp,
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            Image(
//                painter = data.imageRes,
//                contentDescription = null,
//                contentScale = ContentScale.Fit,
//            )
//            Column(Modifier.padding(8.dp)) {
//                Text(
//                    text = data.name,
//                    fontFamily = Poppins,
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    modifier = Modifier
//                        .padding(bottom = 5.dp)
//                        .fillMaxWidth(),
//                    color = Color.Black,
//                )
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(end = 50.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Box() {
//                        Row() {
//                            Icon(
//                                Icons.Default.Star,
//                                contentDescription = "Rate",
//                                tint = Color.Black,
//                                modifier = Modifier.size(14.dp)
//                            )
//                            Text(
//                                text = data.rate,
//                                fontFamily = Poppins,
//                                fontSize = 11.sp,
//                                fontWeight = FontWeight.Normal,
//                                color = Color.Black
//                            )
//                        }
//                    }
//                    Image(
//                        painter = data.country,
//                        contentDescription = "Country",
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .height(15.dp)
//                            .width(26.dp)
//                    )
//                    Card(
//                        modifier = Modifier
//                            .wrapContentSize(),
//                        backgroundColor = Color(0xffF0F1F2)
//                    ) {
//                        Text(
//                            text = data.status,
//                            fontWeight = FontWeight.Normal,
//                            fontFamily = Poppins,
//                            fontSize = 8.sp,
//                            color = Color.Black,
//                            modifier = Modifier
//                                .padding(2.dp)
//                        )
//                    }
//                    Box() {
//                        Row() {
//                            Icon(
//                                Icons.Default.ThumbUp,
//                                contentDescription = "Count",
//                                modifier = Modifier.size(13.dp)
//                            )
//                            Text(
//                                text = data.counts,
//                                fontWeight = FontWeight.Normal,
//                                fontFamily = Poppins,
//                                fontSize = 10.sp,
//                                color = Color.Black
//                            )
//                        }
//                    }
//                }
//                Text(
//                    text = data.description,
//                    fontWeight = FontWeight.Normal,
//                    fontFamily = Poppins,
//                    fontSize = 10.sp,
//                    color = Color.Black,
//                    modifier = Modifier.padding(bottom = 4.dp)
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ActionPreview() {
//    PAMActivityIntentTheme {
//        screenAction()
//    }
//}