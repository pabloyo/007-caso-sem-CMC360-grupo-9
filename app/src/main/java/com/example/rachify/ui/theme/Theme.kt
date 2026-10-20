package com.example.rachify.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = RackifyOrange,
    onPrimary = Color.Black,
    secondary = RackifyWhite,
    onSecondary = Color.Black,
    tertiary = RackifyGreen,
    onTertiary = Color.Black,
    background = RackifyBlack,
    onBackground = RackifyWhite,
    surface = RackifyDarkGray,
    onSurface = RackifyWhite,
    error = RackifyRed,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = RackifyOrange,
    onPrimary = Color.Black,
    secondary = RackifyWhite,
    onSecondary = Color.Black,
    tertiary = RackifyGreen,
    onTertiary = Color.Black,
    background = RackifyBlack,
    onBackground = RackifyWhite,
    surface = RackifyDarkGray,
    onSurface = RackifyWhite,
    error = RackifyRed,
    onError = Color.White
)

@Composable
fun RachifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}