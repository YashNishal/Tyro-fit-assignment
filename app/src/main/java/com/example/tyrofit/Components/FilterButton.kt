package com.example.tyrofit.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tyrofit.Utils.gradientBackground
import com.example.tyrofit.ui.theme.Gradient1
import com.example.tyrofit.ui.theme.Gradient2
import com.example.tyrofit.ui.theme.Gradient3

@Composable
fun FilterButton(Icon : Int,onFilterClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .clip(CircleShape)
            .gradientBackground(
                colors = listOf(
                    Gradient3,
                    Gradient2,
                    Gradient1
                ),
                45f
            )
            .clickable {
                onFilterClick()
            }
            .padding(12.dp)
            .size(24.dp)
        ,
        painter = painterResource(id = Icon),
        contentDescription = "Filter",
        tint = Color.White,
    )
}