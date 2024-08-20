package pe.idat.appbasicmvvm.home.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pe.idat.appbasicmvvm.home.ViewModel.HomeViewModel

@Composable
fun historiaclinicaScreen(homeViewModel: HomeViewModel, usuario:String){
    //Aqui se definen mis listas para iterar
    var alergias = listOf("Alerg1", "Alerg1", "Alerg2", "Alerg")

    //Aqui defino mis variables traidas con un cierto IdPaciente
    var user = usuario
    var idPaciente = 3246
    var paciente = "Jair Fernandez Castillo"
    var dni = "74068095" //Aqui recibo el parametro de la otra vista
    var idHc = 1234
    var sexo = "Masculino"
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 25.dp, end = 25.dp, bottom = 30.dp, top = 60.dp)){
        //Aquí se agregará la vista de la historia médica en Compose
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            Row {
                Text(text = "Historia n° $idHc")
            }
            Row (modifier = Modifier.padding(bottom = 15.dp, top = 10.dp)){
                Column {
                    Text(text = "Nombres y Apellidos")
                    Text(text = "$paciente")
                }
                Column {
                    Text(text = "Id Paciente")
                    Text(text = "$idPaciente")
                }

            }
            Row (modifier = Modifier.padding(bottom = 15.dp, top = 10.dp)){
                Column {
                    Row {Text(text = "DNI")}
                    Row {Text(text = "$dni")}
                }
            }
            Row (modifier = Modifier.padding(bottom = 15.dp, top = 10.dp)){
                Column {
                    Row{Text(text = "Sexo")}
                    Row{Text(text = "$sexo")}
                }
            }
            Row (modifier = Modifier.padding(bottom = 15.dp, top = 10.dp)){
                VerHistoriasD {

                }
            }
            Row(modifier = Modifier.padding(bottom = 30.dp, top = 10.dp)) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier.size(width = 500.dp, height = 100.dp)
                ) {
                    Text(
                        text = "Alergias",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Row(modifier = Modifier.padding(bottom = 30.dp, top = 10.dp)) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier.size(width = 500.dp, height = 100.dp)
                ) {
                    Text(
                        text = "Enfermedades Hereditarias",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }

            }
            Row(modifier = Modifier.padding(bottom = 30.dp, top = 10.dp)) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier.size(width = 500.dp, height = 100.dp)
                ) {
                    Text(
                        text = "Enfermedades Cronicas",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Row(modifier = Modifier.padding( bottom = 30.dp, top = 10.dp)) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier.size(width = 500.dp, height = 100.dp)
                ) {
                    Text(
                        text = "Intervenciones Quirurgicas",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun VerHistoriasD(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(Icons.Filled.DateRange, "Historias Diarias") },
        text = { Text(text = "Ver historias diarias") },
    )
}