package org.capstone_project.travelku.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val ExploreSelected: ImageVector
    get() {
        if (_ExploreSelected != null) {
            return _ExploreSelected!!
        }
        _ExploreSelected = ImageVector.Builder(
            name = "ExploreSelected",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(12f, 10.9f)
                curveToRelative(-0.61f, 0f, -1.1f, 0.49f, -1.1f, 1.1f)
                reflectiveCurveToRelative(0.49f, 1.1f, 1.1f, 1.1f)
                curveToRelative(0.61f, 0f, 1.1f, -0.49f, 1.1f, -1.1f)
                reflectiveCurveToRelative(-0.49f, -1.1f, -1.1f, -1.1f)
                close()
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveToRelative(4.48f, 10f, 10f, 10f)
                reflectiveCurveToRelative(10f, -4.48f, 10f, -10f)
                reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                close()
                moveTo(14.19f, 14.19f)
                lineTo(6f, 18f)
                lineToRelative(3.81f, -8.19f)
                lineTo(18f, 6f)
                lineToRelative(-3.81f, 8.19f)
                close()
            }
        }.build()

        return _ExploreSelected!!
    }

@Suppress("ObjectPropertyName")
private var _ExploreSelected: ImageVector? = null

val OutlineExplore: ImageVector
    get() {
        if (_OutlineExplore != null) {
            return _OutlineExplore!!
        }
        _OutlineExplore = ImageVector.Builder(
            name = "OutlineExplore",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveToRelative(4.48f, 10f, 10f, 10f)
                reflectiveCurveToRelative(10f, -4.48f, 10f, -10f)
                reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                close()
                moveTo(12f, 20f)
                curveToRelative(-4.41f, 0f, -8f, -3.59f, -8f, -8f)
                reflectiveCurveToRelative(3.59f, -8f, 8f, -8f)
                reflectiveCurveToRelative(8f, 3.59f, 8f, 8f)
                reflectiveCurveToRelative(-3.59f, 8f, -8f, 8f)
                close()
                moveTo(6.5f, 17.5f)
                lineToRelative(7.51f, -3.49f)
                lineTo(17.5f, 6.5f)
                lineTo(9.99f, 9.99f)
                lineTo(6.5f, 17.5f)
                close()
                moveTo(12f, 10.9f)
                curveToRelative(0.61f, 0f, 1.1f, 0.49f, 1.1f, 1.1f)
                reflectiveCurveToRelative(-0.49f, 1.1f, -1.1f, 1.1f)
                reflectiveCurveToRelative(-1.1f, -0.49f, -1.1f, -1.1f)
                reflectiveCurveToRelative(0.49f, -1.1f, 1.1f, -1.1f)
                close()
            }
        }.build()

        return _OutlineExplore!!
    }

@Suppress("ObjectPropertyName")
private var _OutlineExplore: ImageVector? = null
