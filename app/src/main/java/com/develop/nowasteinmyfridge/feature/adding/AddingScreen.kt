package com.develop.nowasteinmyfridge.feature.adding

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.develop.nowasteinmyfridge.BottomBarScreen
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.data.model.IngredientCreate
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.Black
import com.develop.nowasteinmyfridge.ui.theme.GrayPrimary
import com.develop.nowasteinmyfridge.ui.theme.GreenButton
import com.develop.nowasteinmyfridge.ui.theme.GreenPrimary
import com.develop.nowasteinmyfridge.ui.theme.White
import com.develop.nowasteinmyfridge.util.Result
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddingScreen(
    navController: NavController,
    addingViewModel: AddingViewModel = hiltViewModel(),
) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var quantity by remember { mutableStateOf(TextFieldValue()) }
    var mfgDate by remember { mutableStateOf(Calendar.getInstance()) }
    var efdDate by remember { mutableStateOf(Calendar.getInstance()) }
    var selectImageUri by remember { mutableStateOf<Uri?>(null) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> selectImageUri = uri }
    )

    val addIngredientResult by addingViewModel.addIngredientResult.collectAsStateWithLifecycle()
    val context = LocalContext.current
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
                    if (selectImageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = selectImageUri),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.mipmap.add_photo),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
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
                            color = White,
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
                                ClickableTextWithPlaceholder(
                                    placeholder = stringResource(id = R.string.mfg_placeholder),
                                    date = mfgDate,
                                    onDateSelected = { mfgDate = it }
                                )
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
                                ClickableTextWithPlaceholderWithNoValue(
                                    placeholder = stringResource(id = R.string.efd_placeholder),
                                    date = null,
                                    onDateSelected = { efdDate = it }
                                )

                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (addIngredientResult is Result.Loading) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CircularProgressIndicator()
                                }
                            } else {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Button(
                                        onClick = {
                                            photoPickerLauncher.launch("image/*")
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = GreenButton),
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.import_image_ingredient),
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Button(
                                        onClick = {
                                            addingViewModel.addIngredient(
                                                IngredientCreate(
                                                    name = name.text,
                                                    quantity = quantity.text.toIntOrNull() ?: 0,
                                                    image = selectImageUri,
                                                    mfg = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(mfgDate.time),
                                                    efd = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(efdDate.time),
                                                )
                                            )
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
                            if (addIngredientResult is Result.Success) {
                                Toast.makeText(context, "Adding Success", Toast.LENGTH_SHORT).show()
                                navController.navigate(BottomBarScreen.Inventory.route)
                            } else if (addIngredientResult is Result.Error) {
                                val error = (addIngredientResult as Result.Error).exception
                                Toast.makeText(
                                    context,
                                    "ERROR: ${error.message.toString()}",
                                    Toast.LENGTH_SHORT,
                                ).show()
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
            onValueChange = {
                if ((it.text.toIntOrNull() ?: 0) > 0) {
                    onValueChange(it)
                }
            },
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
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
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

@Composable
fun ClickableTextWithPlaceholder(
    placeholder: String,
    date: Calendar,
    onDateSelected: (Calendar) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .height(40.dp)
            .clickable {
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, dayOfMonth)
                        onDateSelected(selectedDate)
                    },
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = date.let {
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.time)
                } ?: placeholder,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
                    .background(Color.Transparent)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Start
            )

            Icon(
                imageVector = Icons.Filled.CalendarToday,
                contentDescription = "Calendar",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
                    .clickable {
                        DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                val selectedDate = Calendar.getInstance()
                                selectedDate.set(year, month, dayOfMonth)
                                onDateSelected(selectedDate)
                            },
                            date.get(Calendar.YEAR),
                            date.get(Calendar.MONTH),
                            date.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }
            )
        }
    }
}

@Composable
fun ClickableTextWithPlaceholderWithNoValue(
    placeholder: String,
    date: Calendar?,
    onDateSelected: (Calendar) -> Unit
) {
    val context = LocalContext.current
    val formattedDate = remember { mutableStateOf(placeholder) }

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .height(40.dp)
            .clickable {
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, dayOfMonth)
                        formattedDate.value = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
                        onDateSelected(selectedDate)
                    },
                    date?.get(Calendar.YEAR) ?: Calendar.getInstance().get(Calendar.YEAR),
                    date?.get(Calendar.MONTH) ?: Calendar.getInstance().get(Calendar.MONTH),
                    date?.get(Calendar.DAY_OF_MONTH) ?: Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                ).show()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formattedDate.value,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
                    .background(Color.Transparent)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Start
            )
            Icon(
                imageVector = Icons.Filled.CalendarToday,
                contentDescription = "Calendar",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
                    .clickable {
                        DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                val selectedDate = Calendar.getInstance()
                                selectedDate.set(year, month, dayOfMonth)
                                formattedDate.value = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
                                onDateSelected(selectedDate)
                            },
                            date?.get(Calendar.YEAR) ?: Calendar.getInstance().get(Calendar.YEAR),
                            date?.get(Calendar.MONTH) ?: Calendar.getInstance().get(Calendar.MONTH),
                            date?.get(Calendar.DAY_OF_MONTH) ?: Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddingScreenPreview() {
    AddingScreen(rememberNavController())
}
