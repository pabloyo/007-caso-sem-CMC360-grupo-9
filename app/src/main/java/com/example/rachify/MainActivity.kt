package com.example.rachify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rachify.ui.theme.RachifyTheme
import com.example.rachify.ui.login.LoginScreen
import com.example.rachify.ui.theme.RackifyBlack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                RackifyBlack.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.dark(
                RackifyBlack.toArgb()
            )
        )
        setContent {
            RachifyTheme {
                RackifyApp()
            }
        }
    }
}

@Composable
private fun RackifyApp() {
    var signedInUser by rememberSaveable { mutableStateOf<String?>(null) }

    if (signedInUser == null) {
        LoginScreen(onLoginSuccess = { signedInUser = it })
    } else {
        ScannerPlaceholder(username = signedInUser.orEmpty())
    }
}

@Composable
private fun ScannerPlaceholder(username: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF777777))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "RACKIFY", color = Color(0xFFF6B21A))
        Text(
            text = "Bienvenido, $username",
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "todavia no implementado.",
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RachifyTheme {
        RackifyApp()
    }
}
