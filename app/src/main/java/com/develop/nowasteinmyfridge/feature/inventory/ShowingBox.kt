package com.develop.nowasteinmyfridge.feature.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import com.develop.nowasteinmyfridge.data.model.Ingredient

@Composable
fun ShowingBox(
    ingredient: Ingredient,
) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = size.height / 4,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
        )

        Image(
            painter = rememberImagePainter(data = ingredient.image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = size.height / 4,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
        )

        Text(
            text = ingredient.name,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 6.dp, end = 10.dp, start = 6.dp)
                .zIndex(1f),
            color = Color.White,
        )
    }
}

@Preview
@Composable
fun ShowingBoxPreview() {
    ShowingBox(ingredient = Ingredient(name = "Potato", image = ""))
}