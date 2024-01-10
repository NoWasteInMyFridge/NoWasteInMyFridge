package com.develop.nowasteinmyfridge.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.Black
import com.develop.nowasteinmyfridge.ui.theme.GrayPrimary
import com.develop.nowasteinmyfridge.ui.theme.GreenPrimary
import com.develop.nowasteinmyfridge.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Adding() {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var quantity by remember { mutableStateOf(TextFieldValue()) }


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
                            painter = painterResource(id = R.mipmap.addphoto),
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
                        modifier = Modifier.fillMaxSize().padding(40.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "INVENTORY",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Black,
                        )
                        Text(
                            text = "Add your ingredient",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = GrayPrimary,
                            modifier = Modifier.padding(start = 14.dp)
                        )

                        Text(
                            text = "Ingredient name",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Black,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        InputFieldWithPlaceholder("Name", name) {
                            name = it
                        }

                        Text(
                            text = "Quantity of ingredient",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Black,
                        )
                        InputFieldWithPlaceholder("Quantity", quantity) {
                            quantity = it
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
            .padding(top = 10.dp, bottom = 10.dp)
            .height(30.dp)
            .background(color = GreenPrimary)
            .clip(RoundedCornerShape(20.dp)),
//        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = textValue,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (textValue.text.isEmpty()) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                    modifier = Modifier.padding(start = 4.dp).align(Alignment.CenterStart)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddingScreenPreview() {
    Adding()
}
