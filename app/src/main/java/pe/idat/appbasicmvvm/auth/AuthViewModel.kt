package pe.idat.appbasicmvvm.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _usuario = MutableLiveData<String>()
    val usuario : LiveData<String> = _usuario
    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    fun onLoginValueChanged(usuario:String, password: String){
        _usuario.value = usuario
        _password.value = password
    }
    fun login(): Boolean{
       if(usuario.value == "jfercas" && password.value == "123") {return true}
       else if(usuario.value == "geanc" && password.value == "123"){ return true}
       else if(usuario.value == "jcasa" && password.value == "123"){ return true}
       else if(usuario.value == "jgalan" && password.value == "123"){ return true}
       else if(usuario.value == "spaytan" && password.value == "123"){ return true}
       else if(usuario.value == "admin" && password.value == "123"){ return true}
       else{return false}

    }

}