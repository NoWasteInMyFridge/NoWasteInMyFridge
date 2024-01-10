package com.develop.nowasteinmyfridge.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.data.model.MenuItem
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.GrayPrimary

@Composable
fun BoxNavigation(
    menuItem: MenuItem,
    isFirstItem: Boolean,
    isLastItem: Boolean,
    content: @Composable (Modifier) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(
                when {
                    isFirstItem && isLastItem -> RoundedCornerShape(16.dp)
                    isFirstItem -> RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    isLastItem -> RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    else -> RoundedCornerShape(0.dp)
                }
            )
            .background(Color.White)
            .clickable {
            }
            .drawBehind {
                val lineHeight = 2.dp.toPx()
                if (!isLastItem) {
                    drawLine(
                        start = Offset(10f, size.height),
                        end = Offset(size.width - 10f, size.height),
                        color = GrayPrimary,
                        strokeWidth = lineHeight
                    )
                }
            }


    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = menuItem.icon,
                    contentDescription = null,
                    tint = GrayPrimary,
                    modifier = Modifier
                        .size(34.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = menuItem.nameResId),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = GrayPrimary,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = GrayPrimary,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
            content(Modifier.padding(16.dp))
        }
    }
}

@Composable
fun SliderBoxComponentVertical(menuItems: List<MenuItem>, modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(menuItems) { index, menuItem ->
            BoxNavigation(
                menuItem = menuItem,
                isFirstItem = index == 0,
                isLastItem = index == menuItems.size - 1
            ) {
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Setting() {
    val menuItems = listOf(
        MenuItem(R.string.menu_account, Icons.Default.AccountCircle),
        MenuItem(R.string.menu_setting, Icons.Default.Settings),
    )

    val menuItems2 = listOf(
        MenuItem(R.string.menu_notification, Icons.Default.Notifications),
        MenuItem(R.string.menu_password, Icons.Default.Password),
        MenuItem(R.string.menu_language, Icons.Default.Language),
    )


    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.4f)
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.user_background),
                    contentDescription = "Header Login",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = stringResource(id = R.string.welcome_message),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
            SliderBoxComponentVertical(
                menuItems = menuItems,
                modifier = Modifier
                    .offset(y = (-60).dp)
                    .padding(20.dp)
            )
            SliderBoxComponentVertical(
                menuItems = menuItems2,
                modifier = Modifier
                    .offset(y = (-60).dp)
                    .padding(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun SettingScreenPreview() {
    Setting()
}
