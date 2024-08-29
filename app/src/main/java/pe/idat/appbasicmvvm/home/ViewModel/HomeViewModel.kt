package pe.idat.appbasicmvvm.home.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.appbasicmvvm.auth.PacienteRepository
import pe.idat.appbasicmvvm.core.bd.PacienteEntity
import pe.idat.appbasicmvvm.home.data.network.response.AlbunesResponse
import pe.idat.appbasicmvvm.home.domain.GetHistoriaDiariaUseCase
import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import pe.idat.appbasicmvvm.home.domain.GetAlbunesCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val gethistoriadiariaUseCase: GetHistoriaDiariaUseCase,private val getAlbunesCase: GetAlbunesCase,private val obtenerPaciente : PacienteRepository) : ViewModel() {

    private val _historiadiariaResponse = MutableLiveData<List<HistoriaDiariaResponse>>()
    val historiadiariaResponse: LiveData<List<HistoriaDiariaResponse>> = _historiadiariaResponse

    val paciente : LiveData<PacienteEntity> = obtenerPaciente.obtenerpacientes()

    init {
        listarHds()
        listarPhotos()
    }

    fun listarHds() {
        viewModelScope.launch {
            val response = gethistoriadiariaUseCase()
            _historiadiariaResponse.value = response
        }
    }


    private val _albunesResponse = MutableLiveData<List<AlbunesResponse>>()
    val albunesResponse: LiveData<List<AlbunesResponse>> = _albunesResponse



    fun listarPhotos() {
        viewModelScope.launch {
            val response = getAlbunesCase()
            val filteredResponse = response.filter { it.albumId % 2 == 0 }
            _albunesResponse.value = filteredResponse
        }
    }

}