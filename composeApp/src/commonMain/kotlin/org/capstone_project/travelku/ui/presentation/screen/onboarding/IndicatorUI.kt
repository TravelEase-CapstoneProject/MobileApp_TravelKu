package org.capstone_project.travelku.ui.presentation.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun IndicatorUI(
    pageSize: Int,
    currentPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) {
            Spacer(modifier = Modifier.size(2.dp))
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = if (it == currentPage) selectedColor else unselectedColor)
            )
            Spacer(modifier = Modifier.size(2.5.dp))
        }
    }
}

@Preview
@Composable
fun IndicatorUIPreview1() {

    IndicatorUI(pageSize = 3, currentPage = 0)

}

@Preview()
@Composable
fun IndicatorUIPreview2() {

    IndicatorUI(pageSize = 3, currentPage = 1)

}

@Preview()
@Composable
fun IndicatorUIPreview3() {

    IndicatorUI(pageSize = 3, currentPage = 2)

}