package pe.idat.appbasicmvvm.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun infouserScreen(usuario: String){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)){
        Column {
            Text(text = "Bienvenido $usuario !!")
            Text(text = "Estos son tus datos personales !!")
        }
    }
}