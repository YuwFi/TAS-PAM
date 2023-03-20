package edu.uksw.fti.pam.pamactivityintent.screens

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pamactivityintent.MenuActivity
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.contracts.SignUpContract
import edu.uksw.fti.pam.pamactivityintent.ui.screens.Poppins
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme


@Composable
fun LoginForm() {
    val lContext = LocalContext.current

    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var fNameInput by remember { mutableStateOf("") }
    var lNameInput by remember { mutableStateOf("") }
    var cUser by remember { mutableStateOf("harusnya belum regis") }
    var cPass by remember { mutableStateOf("ini sama diatas") }
    var listProfle: ArrayList<String>?
    val paddingModifier = Modifier.padding(10.dp)

    val getUsernameFormSignUpActivity = rememberLauncherForActivityResult(
        contract = SignUpContract(),
        onResult = {listProfle = it!!
            usernameInput = listProfle!![2]
            passwordInput = listProfle!![3]
            lNameInput = listProfle!![1]
            fNameInput = listProfle!![0]
            cUser = listProfle!![2]
            cPass = listProfle!![3]}
    )
    Box(modifier =  Modifier
        .fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.im_login_kucing_bg),
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(210.dp)
                .align(Alignment.BottomEnd)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(bottom = 30.dp),

                ) {
                Image(
                    painter = painterResource(id = R.drawable.im_logo_black),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredHeight(40.dp)
                )
                Text(
                    text = "MangaDex", Modifier.padding(8.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,)
            }
            Card(
                elevation = 10.dp,
                shape = RoundedCornerShape(7.dp),
            ) {
                Column(
                    modifier = paddingModifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = stringResource(R.string.text_signtitle),
                        Modifier.padding(8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Poppins
                    )

                    Column(modifier = Modifier.padding(start =10.dp, end = 10.dp)) {
                        Text(text = stringResource(R.string.text_usernameoremail),
                            Modifier
                                .padding(top = 8.dp)
                                .padding(start = 5.dp),
                            fontSize = 14.sp,
                            fontFamily = Poppins
                        )

                        OutlinedTextField(
                            value = usernameInput.toString(),
                            onValueChange = {usernameInput = it},

                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                cursorColor = Color(0xffFF6740),
                                focusedBorderColor = Color(0xffFF6740),
                                unfocusedBorderColor = Color(0xffE6E6E6),
                                backgroundColor = Color(0xffE6E6E6),
                            )
                        )

                    }

                    Column(modifier = Modifier.padding(start =10.dp, end = 10.dp)) {
                        Text(text = stringResource(R.string.text_password),
                            Modifier
                                .padding(top = 8.dp)
                                .padding(start = 5.dp),
                            fontSize = 14.sp,
                            fontFamily = Poppins

                        )
                        OutlinedTextField(
                            value = passwordInput.toString(),
                            onValueChange = {passwordInput = it},

                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                cursorColor = Color(0xffFF6740),
                                focusedBorderColor = Color(0xffFF6740),
                                unfocusedBorderColor = Color(0xffE6E6E6),
                                backgroundColor = Color(0xffE6E6E6)
                            )
                        )
                        Text(text = stringResource(R.string.text_forgotpassword),
                            Modifier.padding(bottom = 8.dp),
                            fontSize = 12.sp,
                        )
                    }
                    Row() {
                        Button(
                            onClick = { /*TODO*/
                                val isAuthenticated = doAuth(usernameInput,passwordInput)
                                val isRegistred = doAuthregis(usernameInput,passwordInput,cUser,cPass)
                                if(isAuthenticated||isRegistred)
                                    lContext.startActivity(
                                        Intent(lContext, MenuActivity::class.java)
                                            .apply {
                                                putExtra("nama", listNama(fNameInput,lNameInput))
                                            }
                                    )


                            },
                            modifier = paddingModifier,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFF6740))


                        )
                        {
                            Text(text = stringResource(R.string.text_signin),
                                color = Color.White,
                                fontFamily = Poppins
                            )

                        }
                        Button(
                            onClick = {
                                getUsernameFormSignUpActivity.launch(null)
                            }, modifier = paddingModifier,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFF6740))
                        ) {
                            Text(text = stringResource(R.string.text_register),
                                color = Color.White,
                                fontFamily = Poppins
                            )

                        }
                    }



                }

            }


        }
    }


}

fun listNama(f:String,l:String):ArrayList<String>{
    var firstName: String
    var lastName: String

    firstName = f
    lastName = l

    if (firstName.equals("")){
        firstName = "admin"
    }
    if (lastName.equals("")){
        lastName = "67"
    }

    val list = ArrayList<String>()
    list.add(firstName)
    list.add(lastName)
    return list
}

internal fun doAuth(username: String,password:String): Boolean {
    return (username.equals("admin") && password.equals("admin"))
}

internal fun doAuthregis(username: String,password:String, cU:String,cP:String): Boolean {
    return (username.equals(cU) && password.equals(cP))
}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview(){
    PAMActivityIntentTheme {
        LoginForm()
    }
}

