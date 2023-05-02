package edu.uksw.fti.pam.pamactivityintent.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.ui.screens.Poppins
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme


@Composable
fun SignUp  (
    btnOnClickAction: (String,String,String,String) -> Unit
){

    var usernameInput by remember { mutableStateOf("") }
    var fNameInput by remember { mutableStateOf("") }
    var lNameInput by remember { mutableStateOf("") }
    var passInput by remember { mutableStateOf("") }
    var cPassInput by remember { mutableStateOf("") }

    Box(modifier =  Modifier
        .fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.im_signup_kucing_bg),
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(210.dp)
                .align(Alignment.BottomStart)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.text_signup), Modifier.padding(8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                OutlinedTextField(
                    value = fNameInput.toString(),
                    onValueChange = {fNameInput = it},
                    modifier = Modifier.requiredWidth(160.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color(0xffFF6740),
                        focusedBorderColor = Color(0xffFF6740),
                        unfocusedBorderColor = Color(0xffE6E6E6),
                        backgroundColor = Color(0xffFFFFF),
                    ),
                    label = { Text(
                        text = stringResource(R.string.text_firstname),
                        fontFamily = Poppins) },

                    )
                OutlinedTextField(
                    value = lNameInput.toString(),
                    onValueChange = {lNameInput = it},

                    label = { Text(text = stringResource(R.string.text_lastname),
                        fontFamily = Poppins) },
                    modifier = Modifier.requiredWidth(160.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color(0xffFF6740),
                        focusedBorderColor = Color(0xffFF6740),
                        unfocusedBorderColor = Color(0xffE6E6E6),
                        backgroundColor = Color(0xffFFFFF),
                    )
                )
            }
            OutlinedTextField(
                value = usernameInput.toString(),
                onValueChange = {usernameInput = it},

                label = { Text(text = stringResource(R.string.text_username),
                    fontFamily = Poppins) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = Color(0xffFF6740),
                    focusedBorderColor = Color(0xffFF6740),
                    unfocusedBorderColor = Color(0xffE6E6E6),
                    backgroundColor = Color(0xffFFFFF),
                )
            )
            OutlinedTextField(
                value = passInput.toString(),
                onValueChange = {passInput = it},

                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = stringResource(R.string.text_password),
                    fontFamily = Poppins) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = Color(0xffFF6740),
                    focusedBorderColor = Color(0xffFF6740),
                    unfocusedBorderColor = Color(0xffE6E6E6),
                    backgroundColor = Color(0xffFFFFF),
                )
            )
            OutlinedTextField(
                value = cPassInput.toString(),
                onValueChange = {cPassInput = it},

                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = stringResource(R.string.text_confirmpass),
                    fontFamily = Poppins) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = Color(0xffFF6740),
                    focusedBorderColor = Color(0xffFF6740),
                    unfocusedBorderColor = Color(0xffE6E6E6),
                    backgroundColor = Color(0xffFFFFF),
                )
            )
            Row(

            ){
                Button(onClick = {
                    if(!fNameInput.equals("") && !lNameInput.equals("") && passInput.equals(cPassInput) && !usernameInput.equals("")){
                        btnOnClickAction(fNameInput,lNameInput,usernameInput,passInput)
                    }
                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFF6740)),
                    modifier = Modifier.padding(start = 200.dp)
                ) {
                    Text(text = stringResource(R.string.text_ok),
                        color = Color.White,
                        fontFamily = Poppins)

                }
            }

        }
    }


}

fun listProfile(f:String,l:String,u:String,p:String): ArrayList<String> {
    val list = ArrayList<String>()
    list.add(f)
    list.add(l)
    list.add(u)
    list.add(p)
    return list
}


@Preview(showBackground = true)
@Composable

fun SignUpPreview(){
    PAMActivityIntentTheme {

    }
}