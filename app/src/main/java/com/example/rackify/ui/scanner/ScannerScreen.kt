package com.example.rackify.ui.scanner

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.rackify.ui.components.RackifyBottomBar
import com.example.rackify.ui.components.RackifyButton
import com.example.rackify.ui.components.RackifyDestination
import com.example.rackify.ui.components.RackifyTopBar
import com.example.rackify.ui.loading.LoadingScreen
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange
import kotlinx.coroutines.delay
import java.io.File

@Composable
fun ScannerScreen(
    username: String,
    onScan: () -> Unit,
    onBack: () -> Unit,
    onDashboard: () -> Unit,
    onLogout: () -> Unit,
    onProfile: () -> Unit,
    onLoading: () -> Unit,
    onConfiguration: () -> Unit
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var permisoConcedido by remember { mutableStateOf(false) }
    var mostrarLoading by remember { mutableStateOf(false) }

    LaunchedEffect(bitmap) {
        if (bitmap != null) {
            delay(1500)
            mostrarLoading = true
        }
    }

    // Lanzador de cámara
    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && imageUri != null) {
            val bmp = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            bitmap = bmp
        }
    }

    // Permiso de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        permisoConcedido = granted
        if (granted) {
            val file = File.createTempFile("profile_photo", ".jpg", context.cacheDir)
            file.deleteOnExit()
            val uri = FileProvider.getUriForFile(
                context,
                context.packageName + ".provider",
                file
            )
            imageUri = uri
            takePictureLauncher.launch(uri)
        }
    }
    if (mostrarLoading) {
        LoadingScreen(
            textos = listOf(
                "COMPARANDO BD LOCAL...",
                "COMPARANDO CATÁLOGO GLOBAL...",
                "OBTENIENDO DATOS..."
            ),
            username = username,
            onScan = onScan,
            onBack = onBack,
            onDashboard = onDashboard,
            onLogout = onLogout,
            onConfiguration = onConfiguration,
            onProfile = onProfile
        )
    } else {
        Scaffold(
        containerColor = RackifyBlack,
        topBar = {
            RackifyTopBar(
                username = username,
                onLogout = onLogout,
                onProfile = onProfile
            )
        },
        bottomBar = {
            RackifyBottomBar(
                current = RackifyDestination.ESCANER,
                onConfiguration = onConfiguration,
                onScanner = {},
                onDashboard = onDashboard
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ESCANER",
                color = RackifyOrange,
                fontSize = 30.sp,
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 18.dp, bottom = 8.dp)
            )
            Box(
                modifier = Modifier
                    .size(width = 270.dp, height = 270.dp)
                    .border(width = 5.dp, color = RackifyOrange)
                    .background(color = RackifyDarkGray),

                contentAlignment = Alignment.Center
            ) {
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "Escaneo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Icon(
                        tint = RackifyLightGray,
                        contentDescription = "Content",
                        modifier = Modifier.size(100.dp),
                        imageVector = Icons.Outlined.CameraAlt
                    )

                }
            }
            if (bitmap != null) {

                RackifyButton(
                    onClick = {},
                    loading = true,
                    text = "",
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .height(64.dp)
                )
            }else{
                RackifyButton(
                    onClick = {
                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    },
                    text = "ESCANEAR",
                    modifier = Modifier
                        .height(52.dp)
                        .padding(top = 12.dp)
                )

            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScannerScreenPreview() {
    RackifyTheme {
        ScannerScreen(
            username = "admin",
            onScan = {},
            onLoading = {},
            onBack = {},
            onDashboard = {},
            onLogout = {},
            onProfile = {},
            onConfiguration = {}
        )
    }
}