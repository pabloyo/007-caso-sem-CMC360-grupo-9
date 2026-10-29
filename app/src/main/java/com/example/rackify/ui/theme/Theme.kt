package com.example.rackify.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorScheme = darkColorScheme(
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

val LightColorScheme = lightColorScheme(
    primary = RackifyOrange,
    onPrimary = Color.White,
    secondary = RackifyBlack,
    onSecondary = Color.White,
    tertiary = RackifyGreen,
    onTertiary = Color.White,
    background = RackifyWhite,
    onBackground = RackifyBlack,
    surface = RackifyWhite,
    onSurface = RackifyBlack,
    error = RackifyRed,
    onError = Color.White
)

@Composable
fun RackifyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
