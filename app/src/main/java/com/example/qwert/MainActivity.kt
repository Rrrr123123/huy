package com.example.qwert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.qwert.ui.theme.YourAppTheme
import com.example.qwert.qrCodeImage  // Убедитесь, что этот импорт добавлен

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    QrCodeGeneratorScreen()
                }
            }
        }
    }
}

@Composable
fun QrCodeGeneratorScreen() {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )
        qrCodeImage(data = text, size = 200)  // Используйте правильное имя функции
    }
}

fun qrCodeImage(data: String, size: Int) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YourAppTheme {
        QrCodeGeneratorScreen()
    }
}
