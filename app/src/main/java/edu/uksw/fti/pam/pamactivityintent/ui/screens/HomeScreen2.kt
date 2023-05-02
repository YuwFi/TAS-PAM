package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import edu.uksw.fti.pam.pamactivityintent.HalamanChapter
import edu.uksw.fti.pam.pamactivityintent.Model.View.BaruViewModel
import edu.uksw.fti.pam.pamactivityintent.Model.View.MusimanViewModel
import edu.uksw.fti.pam.pamactivityintent.Model.View.PopularViewModel
import edu.uksw.fti.pam.pamactivityintent.Model.View.TerbaruViewModel
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme



private val popularVm = PopularViewModel()
private val terbaruVm = TerbaruViewModel()
private val musimanVm = MusimanViewModel()
private val baruVm = BaruViewModel()

@Composable
fun HomeScreen2(){
    val lContext = LocalContext.current
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit, block = {
        popularVm.getPopularList()
    })
    LaunchedEffect(Unit, block = {
        terbaruVm.getTerbaruList()
    })
    LaunchedEffect(Unit, block = {
        musimanVm.getMusimanList()
    })
    LaunchedEffect(Unit, block = {
        baruVm.getBaruList()
    })

    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(start = 10.dp)
            .background(MaterialTheme.colorScheme.surface)
    ){
        Column (modifier = Modifier
            .verticalScroll(state = scrollState)
//            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 60.dp)){
            Text(
                text = stringResource(R.string.text_popular),
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 5.dp)
            )

            if(popularVm.errorMessage.isEmpty()){
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(popularVm.popularList.size) {
                            index ->

                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Card(
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .clickable {
                                        lContext.startActivity(
                                        Intent(lContext, HalamanChapter::class.java)
                                            .apply {
                                                putExtra("manga", popularVm.popularList[index].title)
                                            }
                                    ) },
                                shape = RoundedCornerShape(8.dp),
                                backgroundColor = Color.White
                            ) {
                                Row() {
                                    AsyncImage(
                                        model = popularVm.popularList[index].imgUrl,
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                    )
                                    Box(modifier = Modifier
                                        .width(200.dp)
                                        .height(150.dp)
                                        .padding(1.dp)){
                                        Column(modifier = Modifier.padding(5.dp)) {
                                            Text(
                                                text = popularVm.popularList[index].title,
//                        fontFamily = Poppins,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color.Black,
                                                fontSize = 13.sp,
                                                modifier = Modifier
                                                    .requiredHeight(35.dp)
                                            )
                                            Text(
                                                text = popularVm.popularList[index].des,
//                        fontFamily = Poppins,
                                                fontSize = 10.sp,
                                                color = Color.Black,
                                                modifier = Modifier
                                                    .requiredHeight(75.dp)

                                            )
                                            Text(
                                                text = popularVm.popularList[index].author,
//                        fontFamily = Poppins,
                                                fontSize = 13.sp,
                                                color = Color.Black,
                                                fontStyle = FontStyle.Italic,
                                            )
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{
                Column() {
                    Text(text = popularVm.errorMessage)
                    Text(text = "error woy")
                }
            }
            LatestList()
        }
    }
}


@Composable
fun LatestList(){
    val lContext = LocalContext.current
    Column {

        Text(
            text = stringResource(R.string.text_latestrelease),
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 5.dp)
        )
        if(terbaruVm.errorMessage.isEmpty()){
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(terbaruVm.terbaruList.size) {
                        index ->

                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                                .padding(5.dp)
                                .clickable {
                                    lContext.startActivity(
                                        Intent(lContext, HalamanChapter::class.java)
                                            .apply {
                                                putExtra("manga", terbaruVm.terbaruList[index].title)
                                            }
                                    ) },
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.White
                        ) {
                            Row() {
                                AsyncImage(
                                    model = terbaruVm.terbaruList[index].imgUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                )
                                Box(modifier = Modifier
                                    .width(200.dp)
                                    .height(150.dp)
                                    .padding(1.dp)){
                                    Column(modifier = Modifier.padding(5.dp)) {
                                        Text(
                                            text = terbaruVm.terbaruList[index].title,
//                        fontFamily = Poppins,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black,
                                            fontSize = 13.sp,
                                            modifier = Modifier
                                                .requiredHeight(35.dp)
                                        )
                                        Text(
                                            text = terbaruVm.terbaruList[index].des,
//                        fontFamily = Poppins,
                                            fontSize = 10.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .requiredHeight(75.dp)

                                        )
                                        Text(
                                            text = terbaruVm.terbaruList[index].author,
//                        fontFamily = Poppins,
                                            fontSize = 13.sp,
                                            color = Color.Black,
                                            fontStyle = FontStyle.Italic,
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            Column() {
                Text(text = terbaruVm.errorMessage)
                Text(text = "error woy")
            }
        }
        SeasonList()
    }
}

@Composable
fun SeasonList(){
    val lContext = LocalContext.current
    Column (){
        Text(
            text = stringResource(R.string.text_seasonal),
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 5.dp)
        )
        if(musimanVm.errorMessage.isEmpty()){
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(musimanVm.musimanList.size) {
                        index ->

                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                                .padding(5.dp)
                                .clickable {
                                    lContext.startActivity(
                                        Intent(lContext, HalamanChapter::class.java)
                                            .apply {
                                                putExtra("manga", musimanVm.musimanList[index].title)
                                            }
                                    ) },
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.White
                        ) {
                            Row() {
                                AsyncImage(
                                    model = musimanVm.musimanList[index].imgUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                )
                                Box(modifier = Modifier
                                    .width(200.dp)
                                    .height(150.dp)
                                    .padding(1.dp)){
                                    Column(modifier = Modifier.padding(5.dp)) {
                                        Text(
                                            text = musimanVm.musimanList[index].title,
//                        fontFamily = Poppins,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black,
                                            fontSize = 13.sp,
                                            modifier = Modifier
                                                .requiredHeight(35.dp)
                                        )
                                        Text(
                                            text = musimanVm.musimanList[index].des,
//                        fontFamily = Poppins,
                                            fontSize = 10.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .requiredHeight(75.dp)

                                        )
                                        Text(
                                            text = musimanVm.musimanList[index].author,
//                        fontFamily = Poppins,
                                            fontSize = 13.sp,
                                            color = Color.Black,
                                            fontStyle = FontStyle.Italic,
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            Column() {
                Text(text = popularVm.errorMessage)
                Text(text = "error woy")
            }
        }
    }
    RecentList()
}

@Composable
fun RecentList(){
    Column (){
        Text(
            text = stringResource(R.string.text_comingsoon),
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 5.dp)
        )
        if (baruVm.errorMessage.isEmpty()){
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(baruVm.baruList.size) { index ->
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Card(
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                                .padding(5.dp),
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.White
                        ) {
                            Row() {
                                AsyncImage(
                                    model = baruVm.baruList[index].imgUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                )
                            }
                        }
                    }
                }
            }
        }else{

            Text(text = baruVm.errorMessage)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen2Preview() {
    PAMActivityIntentTheme {
        HomeScreen2()
    }
}