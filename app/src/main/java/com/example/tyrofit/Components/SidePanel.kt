package com.example.tyrofit.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tyrofit.ui.theme.HeadingTextColor
import com.example.tyrofit.ui.theme.SidePanelBackground
import com.example.tyrofit.R

@Composable
fun SidePanel(parentWidth: Int, onFilterClick: () -> Unit) {

    Column(
        modifier = Modifier
//            .offset(x = (parentWidth/2).dp) // not working
            .offset(x = 200.dp)
            .fillMaxHeight()
            .fillMaxWidth(0.5f)
            .background(SidePanelBackground)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Filters",
                fontSize = 22.sp,
                color = HeadingTextColor,
                fontWeight = FontWeight.Bold
            )
            FilterButton(Icon = R.drawable.ic_baseline_check_24, onFilterClick = onFilterClick)
        }
        Spacer(modifier = Modifier.padding(32.dp))

        // Duration
        Text(
            text = "Duration",
            modifier = Modifier.padding(top = 10.dp),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.padding(32.dp))

        // Trainer
        Text(
            text = "Trainer",
            modifier = Modifier.padding(top = 10.dp),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.padding(32.dp))

        // Difficulty
        Text(
            text = "Difficulty",
            modifier = Modifier.padding(top = 10.dp),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )

    }
}