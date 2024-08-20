package pe.idat.appbasicmvvm.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun homeScreen(dni: String){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)){
        Text(text = "Bienvenido $dni !!",
            modifier = Modifier.align(Alignment.Center))
    }
}