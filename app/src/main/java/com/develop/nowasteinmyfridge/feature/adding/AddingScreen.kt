package com.develop.nowasteinmyfridge.feature.adding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.feature.inventory.DataViewModel
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.Black
import com.develop.nowasteinmyfridge.ui.theme.GrayPrimary
import com.develop.nowasteinmyfridge.ui.theme.GreenButton
import com.develop.nowasteinmyfridge.ui.theme.GreenPrimary
import com.develop.nowasteinmyfridge.ui.theme.White


@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddingScreen(dataViewModel: DataViewModel) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var quantity by remember { mutableStateOf(TextFieldValue()) }
    var mfg by remember { mutableStateOf(TextFieldValue()) }
    var efd by remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 48.dp)
            .background(color = BaseColor)
    ) {
        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.35f)
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.add_photo),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterStart)
                            .padding(start = 20.dp, top = 16.dp)
                    ) {
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = (-24).dp)
                        .background(
                            White,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 40.dp, start = 20.dp, end = 20.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.inventory),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Black,
                        )
                        Text(
                            text = stringResource(id = R.string.inventory_info),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = GrayPrimary,
                            modifier = Modifier.padding(start = 14.dp)
                        )

                        Text(
                            text = stringResource(id = R.string.ingredient_name),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Black,
                            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp)
                        )
                        InputFieldWithPlaceholder(
                            placeholder = stringResource(id = R.string.name_placeholder),
                            textValue = name,
                        ) {
                            name = it
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.quantity),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Black,
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )
                        InputFieldWithPlaceholderWithBorder(
                            placeholder = stringResource(id = R.string.quantity_placeholder),
                            textValue = quantity,
                        ) {
                            quantity = it
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp),
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Text(
                                    text = stringResource(id = R.string.manufacturing_date),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Black,
                                    modifier = Modifier
                                        .padding(bottom = 10.dp)
                                )
                                InputFieldWithPlaceholder(
                                    placeholder = stringResource(id = R.string.mfg_placeholder),
                                    textValue = mfg,
                                ) {
                                    mfg = it
                                }
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = stringResource(id = R.string.expiration_date),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Black,
                                    modifier = Modifier
                                        .padding(bottom = 10.dp)
                                )
                                InputFieldWithPlaceholder(
                                    stringResource(id = R.string.efd_placeholder),
                                    efd,
                                ) {
                                    efd = it
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Button(
                                    onClick = { /* Handle image import */ },
                                    colors = ButtonDefaults.buttonColors(containerColor = GreenButton),
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.import_image_ingredient),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
//                            Spacer(modifier = Modifier.height(16.dp))
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Button(
                                    onClick = {
                                        // Push data to ViewModel
                                        dataViewModel.pushDataToFirestore(
                                            name.text,
                                            quantity.text.toIntOrNull() ?: 0,
                                            "", // Provide the image URL if available
                                            mfg.text,
                                            efd.text
                                        )

                                        name = TextFieldValue("")
                                        quantity = TextFieldValue("")
                                        mfg = TextFieldValue("")
                                        efd = TextFieldValue("")

//                                        LocalSoftwareKeyboardController.current?.hide()
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = GreenButton),
                                )
                                    {
                                    Text(
                                        text = stringResource(id = R.string.add_ingredient),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputFieldWithPlaceholder(
    placeholder: String,
    textValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .height(40.dp)
    ) {
        BasicTextField(
            value = textValue,
            onValueChange = { onValueChange(it) },
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .align(Alignment.Center)
                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),

        )

        if (textValue.text.isEmpty()) {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    .background(Color.Transparent)
                    .align(Alignment.Center),
                textAlign = TextAlign.Start
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputFieldWithPlaceholderWithBorder(
    placeholder: String,
    textValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .height(40.dp)
            .background(GreenPrimary, RoundedCornerShape(10.dp))
    ) {
        BasicTextField(
            value = textValue,
            onValueChange = { onValueChange(it) },
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .align(Alignment.Center)
                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),

        )

        if (textValue.text.isEmpty()) {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    .background(Color.Transparent)
                    .align(Alignment.Center),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddingScreenPreview() {
    val dataViewModel: DataViewModel = viewModel()
    AddingScreen(dataViewModel = dataViewModel)
}
