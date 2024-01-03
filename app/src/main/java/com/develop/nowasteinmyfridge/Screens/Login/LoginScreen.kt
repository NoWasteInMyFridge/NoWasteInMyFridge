package com.develop.nowasteinmyfridge.Screens.Login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.Screen


@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusUsername = remember { FocusRequester() }
    val focusPassword = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.15f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.toppart),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }

            Text(
                text = stringResource(id = R.string.welcome_message),
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 60.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusUsername),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { focusPassword.requestFocus() }),
                    singleLine = true,
                    label = { Text(text = stringResource(id = R.string.username)) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusPassword),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = R.string.password)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
            }
            Text(text = stringResource(id = R.string.forget_password))
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.MainScreen.route)
                },
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = stringResource(id = R.string.login))
            }
            Text(text = stringResource(id = R.string.need_sign_in))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.4f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bottompart),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}
