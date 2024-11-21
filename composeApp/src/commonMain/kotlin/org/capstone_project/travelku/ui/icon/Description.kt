package org.capstone_project.travelku.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Description: ImageVector
    get() {
        if (_Description != null) {
            return _Description!!
        }
        _Description = ImageVector.Builder(
            name = "Description",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(14f, 2f)
                lineTo(6f, 2f)
                curveToRelative(-1.1f, 0f, -1.99f, 0.9f, -1.99f, 2f)
                lineTo(4f, 20f)
                curveToRelative(0f, 1.1f, 0.89f, 2f, 1.99f, 2f)
                lineTo(18f, 22f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                lineTo(20f, 8f)
                lineToRelative(-6f, -6f)
                close()
                moveTo(16f, 18f)
                lineTo(8f, 18f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(8f)
                verticalLineToRelative(2f)
                close()
                moveTo(16f, 14f)
                lineTo(8f, 14f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(8f)
                verticalLineToRelative(2f)
                close()
                moveTo(13f, 9f)
                lineTo(13f, 3.5f)
                lineTo(18.5f, 9f)
                lineTo(13f, 9f)
                close()
            }
        }.build()

        return _Description!!
    }

@Suppress("ObjectPropertyName")
private var _Description: ImageVector? = null
