package pe.idat.appbasicmvvm.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.idat.appbasicmvvm.home.ViewModel.HomeViewModel
import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse

@Composable
fun historiadiariaScreen(homeViewModel: HomeViewModel){
    val historiadiaria by homeViewModel.historiadiariaResponse.observeAsState(emptyList())
    //Column {
    //    Text(text = "Valor de prueba $usuario")
    //}
    LazyColumn{
      items(historiadiaria){
            historiadiaria -> historiadiariaItem(historiadiaria = historiadiaria)
        }
    }

}

@Composable
fun historiadiariaItem(historiadiaria: HistoriaDiariaResponse) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ))
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                    /*
                    Row {
                        Text(text = "Fecha de Creacion ${historiadiaria.Dia}/${historiadiaria.Mes}/${historiadiaria.Anio}", fontWeight = FontWeight.Bold)
                    }
                    Text(text = "Acompañante: ${historiadiaria.Acompaniante}", color = Color.Gray)
                    Text(text = "Peso: ${historiadiaria.Peso}", fontWeight = FontWeight.Bold)
                    Text(text = "Temperatura: ${historiadiaria.Temperatura}", color = Color.Gray)
                    Text(text = "Presion: ${historiadiaria.Presion}", color = Color.Gray)
                    Text(text = "Diagnostico: ${historiadiaria.Diagnostico}", color = Color.Gray)
                    Text(text = "Tratamiento: ${historiadiaria.Tratamiento}", color = Color.Gray)
                    Text(text = "Doctor: ${historiadiaria.Doctor}", fontWeight = FontWeight.Bold)
                    */

                    Text(text = "Acompañante: ${historiadiaria.userId}", color = Color.Gray)
                    Text(text = "Peso: ${historiadiaria.id}", fontWeight = FontWeight.Bold)
                    Text(text = "Temperatura: ${historiadiaria.title}", color = Color.Gray)
                    Text(text = "Presion: ${historiadiaria.body}", color = Color.Gray)

            }
        }
    }
}