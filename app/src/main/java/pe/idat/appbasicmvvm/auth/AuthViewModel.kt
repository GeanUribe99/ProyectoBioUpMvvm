package pe.idat.appbasicmvvm.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.appbasicmvvm.core.bd.PacienteEntity
import pe.idat.appbasicmvvm.home.domain.GetAlbunesCase
import pe.idat.appbasicmvvm.home.domain.GetHistoriaDiariaUseCase
import javax.inject.Inject
@HiltViewModel
class AuthViewModel @Inject constructor(private val pacienteRepository: PacienteRepository): ViewModel() {

    private val _usuario = MutableLiveData<String>()
    val usuario : LiveData<String> = _usuario
    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    var _mensaje:String = ""

    private val pacientes = listOf(
        PacienteEntity(1, "123", "Juan Pérez", "999999999"),
        PacienteEntity(2, "456", "Ana Gómez", "888888888"),
        PacienteEntity(3, "789", "Luis Fernández", "777777777")
    )
    fun onLoginValueChanged(usuario:String, password: String){
        _usuario.value = usuario
        _password.value = password
    }
    fun login(): Boolean {
        val username = usuario.value
        val pass = password.value

        val matchingPatient = pacientes.find { it.codPaciente.toString() == username && it.password == pass }

        if (matchingPatient != null) {
            // Encontramos un paciente coincidente, ahora lo registramos
            registrarPaciente(matchingPatient)
            _mensaje = "Inicio de sesión exitoso. ¡Usuario registrado!"
            return true
        } else {
            _mensaje = "Usuario o contraseña incorrectos."
            return false
        }
    }

    fun registrarPaciente(pacienteEntity: PacienteEntity) {
        viewModelScope.launch {
            pacienteRepository.insertarPacientes(pacienteEntity)
        }
    }
}