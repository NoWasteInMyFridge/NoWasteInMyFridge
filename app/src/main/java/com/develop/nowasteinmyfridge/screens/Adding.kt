package com.develop.nowasteinmyfridge.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.Black
import com.develop.nowasteinmyfridge.ui.theme.GrayPrimary
import com.develop.nowasteinmyfridge.ui.theme.GreenPrimary
import com.develop.nowasteinmyfridge.ui.theme.PrimaryColor
import com.develop.nowasteinmyfridge.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Adding() {
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
                            modifier = Modifier.padding(top = 40.dp)
                        )
                        InputFieldWithPlaceholder(stringResource(id = R.string.name_placeholder), name) {
                            name = it
                        }

                        Text(
                            text = stringResource(id = R.string.quantity),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        InputFieldWithPlaceholderWithBorder(stringResource(id = R.string.quantity_placeholder), quantity) {
                            quantity = it
                        }
                        Row(
                            modifier = Modifier.fillMaxSize().padding(top = 10.dp),
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = stringResource(id = R.string.manufacturing_date),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Black,
//                                    modifier = Modifier.padding(top = 14.dp)
                                )
                                InputFieldWithPlaceholder(stringResource(id = R.string.mfg_placeholder), mfg) {
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
                                )
                                InputFieldWithPlaceholder(stringResource(id = R.string.efd_placeholder), efd) {
                                    efd = it
                                }
                                Button(
                                    onClick = {
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 80.dp)

                                ) {
                                    Text(
                                        text = stringResource(id = R.string.add),
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

@Composable
fun InputFieldWithPlaceholder(
    placeholder: String,
    textValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White)
    ) {
        TextField(
            value = textValue.text,
            onValueChange = { onValueChange(TextFieldValue(it)) },
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .fillMaxSize(),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                cursorColor = Color.Black
//            )
        )

        if (textValue.text.isEmpty()) {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart),
                textAlign = TextAlign.Start
            )
        }
    }
}


@Composable
fun InputFieldWithPlaceholderWithBorder(
    placeholder: String,
    textValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .height(36.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp),
            )
            .border(
                width = 1.dp,
                color = GrayPrimary,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        BasicTextField(
            value = textValue,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = TextStyle(
                color = PrimaryColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            if (textValue.text.isEmpty()) {
                Text(
                    text = placeholder,
                    color = GreenPrimary,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                    modifier = Modifier
                        .padding(start = 4.dp, top = 8.dp)
                        .align(Alignment.CenterStart),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddingScreenPreview() {
//    Adding()
    var efd by remember { mutableStateOf(TextFieldValue()) }
    InputFieldWithPlaceholderWithBorder(stringResource(id = R.string.efd_placeholder), efd) {
        efd = it
    }
}


