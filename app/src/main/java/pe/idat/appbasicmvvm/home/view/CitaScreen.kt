package pe.idat.appbasicmvvm.home.view

import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import pe.idat.appbasicmvvm.R

@Composable
fun citasScreen(context: Context) {
    val scrollState = rememberScrollState()
    val doctorName = "Dr. Juan Pérez"
    val especialidad = "Cardiología"
    val fecha = remember {
        mutableStateOf("")
    }
    val seleccionarTurno = remember {
        mutableStateOf("")
    }
    val seleccionarHora = remember {
        mutableStateOf("")
    }
    val expanded = remember {
        mutableStateOf(false)
    }
    val selectedSede = remember {
        mutableStateOf("Seleccione Sede")
    }
    val expandirHora = remember { mutableStateOf(true) }
    val turnoDisponibles = listOf("8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM")
    val turnoTarde = listOf("2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM")
    val sedes = listOf(
        "Centro Medico Lima",
        "Clinica Surco",
        "Hospital Central",
        "Centro de Salud Miraflores"
    )
    val dropdownMenuSize = remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState) // Permite hacer el scroll vertical
    ) {
        //Información del Doctor
        Text(text = doctorName, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Text(text = especialidad, fontSize = 18.sp, color = Color.Gray)

        Spacer(modifier = Modifier.size(16.dp))

        //Seleccionar Sede
        Text(text = "Seleccione Sede", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        OutlinedButton(
            onClick = { expanded.value = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .onGloballyPositioned { coordinada ->
                    dropdownMenuSize.value = coordinada.size.toSize()
                }
        ) {
            Text(text = selectedSede.value)
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth(),
        ) {
            sedes.forEach { sede ->
                DropdownMenuItem(text = { Text(text = sede) }, onClick = {
                    selectedSede.value = sede
                    expanded.value = false
                })
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        //Calendario
        Button(onClick = { mostrarSelectorFecha(context, fecha) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = fecha.value.ifEmpty { "Seleccionar Fecha" })
        }

        Spacer(modifier = Modifier.size(16.dp))

        //Seleccion de Turno
        Text(text = "Seleccionar Turno", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { seleccionarTurno.value = "Mañana"
                    expandirHora.value = true
                },
                enabled = seleccionarTurno.value != "Mañana",
                modifier = Modifier.weight(1f),
                colors = if (seleccionarTurno.value == "Mañana") {
                    androidx.compose.material3.ButtonDefaults.buttonColors(
                        Color.Gray
                    )
                } else {
                    androidx.compose.material3.ButtonDefaults.buttonColors()
                }
            ) {
                Text(text = "Mañana")
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(
                onClick = {seleccionarTurno.value = "Tarde"
                    expandirHora.value = true
                },
                enabled = seleccionarTurno.value != "Tarde",
                modifier = Modifier.weight(1f),
                colors = if (seleccionarTurno.value == "Tarde"){
                    androidx.compose.material3.ButtonDefaults.buttonColors(
                        Color.Gray
                    )
                } else {
                    androidx.compose.material3.ButtonDefaults.buttonColors()
                }
            ) {
                Text(text = "Tarde")
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        //Seleccionar Hora
        Text(text = "Seleccionar la Hora", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        TextButton(
            onClick = { expandirHora.value = !expandirHora.value }
        ) {
            Text(text = if (expandirHora.value)"Minimizar" else "Expandir")
        }
        AnimatedVisibility(
            visible = expandirHora.value,
            enter = androidx.compose.animation.fadeIn(animationSpec = tween(300)),
            exit = androidx.compose.animation.fadeOut(animationSpec = tween(300))
        ) {
            if (seleccionarTurno.value == "Mañana") {
                OpcionesTiempo(turnoDisponibles, seleccionarHora, expandirHora)
            } else if (seleccionarTurno.value == "Tarde") {
                OpcionesTiempo(turnoTarde, seleccionarHora, expandirHora)
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        //Boton de Reservar
        Button(onClick = {
            if (fecha.value.isNotEmpty() && seleccionarTurno.value.isNotEmpty() && seleccionarHora.value.isNotEmpty()) {
                Toast.makeText(
                    context,
                    "Cita reservada para ${fecha.value} a las ${seleccionarHora.value}",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(context, "Por favor complete todos los campos", Toast.LENGTH_LONG)
                    .show()
            }
        }) {
            Text(text = "Reservar Cita")
        }
    }
}

@Composable
fun OpcionesTiempo(times: List<String>, seleccionarHora: MutableState<String>, expandirHora:MutableState<Boolean>) {
    Column {
        times.forEach { time ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = time)
                IconButton(
                    onClick = {
                        seleccionarHora.value = time
                        expandirHora.value = false // minimiza al seleccionar la hora
                    }) {
                    //Icono de seleccion
                    Icon(
                        painter = if (seleccionarHora.value == time)
                            painterResource(R.drawable.ic_check) else painterResource(R.drawable.ic_uncheck),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

fun mostrarSelectorFecha(context: Context, date: MutableState<String>) {
    val calendar = Calendar.getInstance()
    val seleccionarFecha = android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    seleccionarFecha.show()
}