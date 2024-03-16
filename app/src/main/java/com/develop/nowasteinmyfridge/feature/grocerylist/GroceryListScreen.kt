package com.develop.nowasteinmyfridge.feature.grocerylist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.develop.nowasteinmyfridge.data.model.GroceryListCreate
import com.develop.nowasteinmyfridge.data.model.IngredientCreate
import com.develop.nowasteinmyfridge.feature.grocerylist.GroceryListViewModel
import com.develop.nowasteinmyfridge.ui.theme.GrayPrimary
import com.develop.nowasteinmyfridge.ui.theme.YellowBtn
import androidx.compose.runtime.collectAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroceryListScreen(
    groceryListViewModel: GroceryListViewModel = hiltViewModel(),
) {
    val groceryListState = groceryListViewModel.groceryListState.value
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = com.develop.nowasteinmyfridge.ui.theme.BaseGreen),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(720.dp)
                    .background(
                        color = androidx.compose.ui.graphics.Color.White,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 50.dp
                        )
                    ),
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,

                    )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    )
                    {

                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Grocery List",
                            fontSize = 28.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        LazyColumn {
                            items(groceryListState.size) { index ->
                                val groceryItem = groceryListState[index]
                                GroceryList(groceryItem.name, groceryItem.quantity)
                            }
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 60.dp)
                            .padding(end = 16.dp)
                    ) {
                        PlusButton(groceryListViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun GroceryList(name: String, quantity: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(color = com.develop.nowasteinmyfridge.ui.theme.BasePrimaryGray)
            .padding(bottom= 10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(18.dp)
                .height(46.dp)
                .background(color = com.develop.nowasteinmyfridge.ui.theme.BaseGreen)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
            ) {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxHeight()
                        .width(120.dp),
                ) {
                    Text(
                        text = quantity.toString(),
                        fontSize = 18.sp,
                        color = com.develop.nowasteinmyfridge.ui.theme.BaseGray,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}


@Composable
fun PlusButton(groceryListViewModel: GroceryListViewModel) {
    val showDialog = remember { mutableStateOf(false) }
    val nameState = remember { mutableStateOf("") }
    val quantityState = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .size(68.dp)
            .clip(CircleShape)
            .background(YellowBtn)
            .clickable(onClick = { showDialog.value = true }),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "plus btn",
            modifier = Modifier
                .size(40.dp)
                .padding(2.dp),
            tint = GrayPrimary
        )

        if (showDialog.value) {
            Dialog(
                onDismissRequest = { showDialog.value = false }
            ) {
                Column(
                    modifier = Modifier
                        .background(com.develop.nowasteinmyfridge.ui.theme.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Enter details:",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        value = nameState.value,
                        onValueChange = { nameState.value = it },
                        label = { Text("Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = quantityState.value,
                        onValueChange = { quantityState.value = it },
                        label = { Text("Quantity") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        TextButton(onClick = { showDialog.value = false }) {
                            Text(
                                text = "Cancel",
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextButton(
                            onClick = {
                                showDialog.value = false
                                val quantity = quantityState.value.toIntOrNull()
                                if (quantity != null) {
                                    groceryListViewModel.addGroceryList(
                                        GroceryListCreate(
                                            name = nameState.value,
                                            quantity = quantity
                                        )
                                    )
                                }
                            }
                        ) {
                            Text(text = "Confirm")
                        }

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GroceryListScreenPreview() {
    GroceryListScreen()
}