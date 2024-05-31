package com.example.qwert

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun qrCodeImage(data: String, size: Int) {
    val bitmap = rememberQrBitmap(data, size)
    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(size.dp)
        )
    } else {
        CircularProgressIndicator()
    }
}

@Composable
fun rememberQrBitmap(data: String, size: Int): Bitmap? {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    LaunchedEffect(data, size) {
        withContext(Dispatchers.Default) {
            try {
                val bitMatrix = MultiFormatWriter().encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    size,
                    size
                )
                val width = bitMatrix.width
                val height = bitMatrix.height
                val pixels = IntArray(width * height)
                for (y in 0 until height) {
                    for (x in 0 until width) {
                        pixels[y * width + x] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                    }
                }
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                bmp.setPixels(pixels, 0, width, 0, 0, width, height)
                bitmap = bmp
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    return bitmap
}
