package pe.idat.appbasicmvvm.auth
/*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun registroScreen(
    //registroViewModel: RegistroViewModel,
    navController: NavController
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) })
    { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            cabeceraRegistro()
            formularioRegistro(registroViewModel, snackbarHostState, navController)
        }
    }
}

@Composable
fun cabeceraRegistro() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.SupervisedUserCircle, contentDescription = "logo", // CAMBIAR!!!!
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
        Text(text = "REGISTRAR USUARIO", fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun formularioRegistro(registroViewModel: RegistroViewModel,
                       hostState: SnackbarHostState,
                       navController: NavController
){
    val dni: String by registroViewModel.dni.observeAsState(initial = "")
    val fechaNacimiento: String by registroViewModel.fechaNacimiento.observeAsState(initial = "")
    val celular: String by registroViewModel.celular.observeAsState(initial = "")
    val correo: String by registroViewModel.correo.observeAsState(initial = "")
    val password: String by registroViewModel.password.observeAsState(initial = "")

    Column(
        Modifier
            .padding(start = 5.dp, end = 5.dp)
            .verticalScroll(rememberScrollState())) {
        txtDniReg(dni) { registroViewModel.onRegistroChanged(it, fechaNacimiento, celular,
            correo, password) }
        Spacer(modifier = Modifier.size(4.dp))
        txtFechaNacimientoReg(fechaNacimiento) { registroViewModel.onRegistroChanged(dni, it, celular,
            correo, password) }
        Spacer(modifier = Modifier.size(4.dp))
        txtCelularReg(celular) { registroViewModel.onRegistroChanged(dni, fechaNacimiento, it,
            correo, password) }
        Spacer(modifier = Modifier.size(4.dp))
        txtCorreoReg(correo) { registroViewModel.onRegistroChanged(dni, fechaNacimiento, celular,
            it, password) }
        Spacer(modifier = Modifier.size(4.dp))
        txtPasswordReg(password) { registroViewModel.onRegistroChanged(dni, fechaNacimiento, celular,
            correo, it) }
        Spacer(modifier = Modifier.size(4.dp))
        registroButton(registroViewModel,hostState)
        Spacer(modifier = Modifier.size(4.dp))
        irLoginButton(navController)
    }
}

@Composable
fun registroButton(registroViewModel: RegistroViewModel,
                   state: SnackbarHostState
){
    val registroResponse by registroViewModel.registroResponse.observeAsState()
    val scope = rememberCoroutineScope()
    Button(onClick = {
        registroViewModel.registrarPersona()
    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "REGISTRAR USUARIO")
    }
    registroResponse?.getContentNotChange()?.let {
            response ->
        scope.launch {
            state.showSnackbar(response.mensaje,
                actionLabel = "OK",
                duration = SnackbarDuration.Short)
        }
        registroViewModel.setearRegistro()
    }
}

@Composable
fun irLoginButton(navController: NavController){
    Button(onClick = {
        navController.navigate(Ruta.loginScreen.path)
    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "REGRESAR A LOGIN")
    }
}

@Composable
fun txtDniReg(dni: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(value = dni,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Dni") },
        maxLines = 1,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "persona") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable //CAMBIAR!!!
fun txtFechaNacimientoReg(fechaNacimiento: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(value = fechaNacimiento,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Fecha de Nacimiento") },
        maxLines = 1,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "persona") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun txtCelularReg(celular: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(value = celular,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Celular") },
        maxLines = 1,
        leadingIcon = { Icon(imageVector = Icons.Default.PhoneAndroid, contentDescription = "email") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )
}

@Composable
fun txtCorreoReg(correo: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(value = correo,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Correo") },
        maxLines = 1,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun txtPasswordReg(password: String, onTextChanged: (String) -> Unit) {
    var visible by rememberSaveable {
        mutableStateOf(false)
    }
    OutlinedTextField(value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        leadingIcon = { Icon(imageVector = Icons.Default.Key, contentDescription = "persona") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if(visible){
                Icons.Filled.VisibilityOff
            }else Icons.Filled.Visibility
            IconButton(onClick = { visible = !visible }) {
                Icon(imageVector = icon, contentDescription = "ver password")
            }
        },
        visualTransformation = if(visible){
            VisualTransformation.None
        } else PasswordVisualTransformation()
    )
}*/