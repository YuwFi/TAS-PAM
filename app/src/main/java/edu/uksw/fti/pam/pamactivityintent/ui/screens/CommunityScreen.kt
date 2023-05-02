package edu.uksw.fti.pam.pamactivityintent.ui.screens


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import edu.uksw.fti.pam.pamactivityintent.Model.Object.CommunityModel
import edu.uksw.fti.pam.pamactivityintent.Model.View.CommunityViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

private val communityVm = CommunityViewModel()

@Composable
fun CommunityScreen(){
    LaunchedEffect(Unit, block = {
        communityVm.getCommunityList()
    })
//    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .verticalScroll(state = scrollState)
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 60.dp)
    ) {
        Text(
            text = "Community",
            fontFamily = Poppins,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 10.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        if(communityVm.errorMessage.isEmpty()){
            LazyColumn(
                Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(communityVm.communityList.size) { index ->
                    CommunityCard(communityVm.communityList[index])
                }
            }
        }
        else{
            Column() {
                Text(text = communityVm.errorMessage)
                Text(text = "error woy")
            }


        }

    }
}

@Composable
fun CommunityCard(data: CommunityModel){
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(PaddingValues(top = 8.dp)),
        elevation = 12.dp,
        shape = RoundedCornerShape(size = 30.dp)

    ) {
        Row(
            modifier = Modifier.padding(all = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(size = 62.dp),
                model = data.imgUrl,
                contentDescription = "null",
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = data.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable

fun CommunityScreenPreview(){
    PAMActivityIntentTheme {
        CommunityScreen()
    }
}