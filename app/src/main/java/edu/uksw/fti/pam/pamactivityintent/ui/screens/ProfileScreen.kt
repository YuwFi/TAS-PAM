package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pamactivityintent.PermissionActivity
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

@Composable
fun ProfileScreen(fname:String?,lname:String?) {
    var firstName  by remember { mutableStateOf("")}
    var lastName by remember { mutableStateOf("") }
    val lContext = LocalContext.current


    firstName = fname.toString()
    lastName = lname.toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
        ){
            Image(
                painter = painterResource(id = R.drawable.im_banner_profile_1),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = R.drawable.im_profile_screen_kucing),
                contentDescription = null,
                modifier = Modifier
                    .requiredHeight(150.dp)
                    .align(Alignment.Center)
                    .padding(10.dp)

            )
        }
        Row(

            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = { /*TODO*/
                    lContext.startActivity(
                        Intent(lContext, PermissionActivity::class.java)
                    )
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_more_horiz_24),
                    contentDescription = null,
                    modifier = Modifier.requiredWidth(20.dp)
                )

            }
            Button(
                onClick = {
                },
                modifier = Modifier.requiredWidth(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFF6740))
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_bookmark2),
                    contentDescription = null,
                    modifier = Modifier.requiredWidth(20.dp)
                )
                Text(text = stringResource(R.string.follow),
                    color = Color.White,
                    fontFamily = Poppins)

            }

        }
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "$firstName $lastName",
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp,
                color = Color.Black,
            )
            Column() {
                Text(
                    text = stringResource(R.string.firstname),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = Poppins
                )

                Text(
                    text = "$firstName ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = Poppins
                )

            }
            Column() {
                Text(
                    text = stringResource(R.string.lastname),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = Poppins
                )

                Text(
                    text = "$lastName ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = Poppins
                )

            }

            Column() {
                Text(
                    text = stringResource(R.string.userid),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = Poppins
                )

                Text(
                    text = "9a636b76-1f68-45ba-975e-75c36dd676aa",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = Poppins
                )

            }
            Column() {
                Text(
                    text = stringResource(R.string.roles),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = Poppins
                )

                Card (
                    backgroundColor = Color(0xffF0F1F2),
                    shape = RoundedCornerShape(1.dp),
                ){  Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_role_dot_white),
                        contentDescription = null,
                        modifier = Modifier.requiredWidth(10.dp),
                        tint = Color.Unspecified
                    )

                    Text(
                        text = stringResource(R.string.member),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(1.dp),
                        fontFamily = Poppins
                    )
                }

                }

            }
            Column() {
                Text(
                    text = stringResource(R.string.uploads),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = Poppins
                )

                Text(
                    text = "0",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = Poppins
                )

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PAMActivityIntentTheme {
        ProfileScreen(fname = "Alief", lname = "Yuwastika")
    }
}