package com.example.tyrofit.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tyrofit.Utils.gradientBackground
import com.example.tyrofit.ui.theme.*
import com.example.tyrofit.R



@Composable
fun TopBar(onFilterClick : () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton()
        TitleText()
        FilterButton(R.drawable.ic_funnel,onFilterClick)
    }
}





@Composable
fun BackButton() {
    Icon(
        modifier = Modifier
            .clip(CircleShape)
            .background(BackButtonColor)
            .padding(10.dp)
            .size(26.dp),
        painter = painterResource(id = R.drawable.back_icon),
        contentDescription = "Back",
        tint = Color.White
    )
}


@Composable
fun TitleText(text: String = "Cardio") {
    Text(
        text = text,
        fontSize = 22.sp,
        color = HeadingTextColor,
        fontWeight = FontWeight.Bold
    )
}


