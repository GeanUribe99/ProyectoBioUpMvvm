package pe.idat.appbasicmvvm.home.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.appbasicmvvm.home.data.network.response.AlbunesResponse
import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import pe.idat.appbasicmvvm.home.domain.GetAlbunesCase
import pe.idat.appbasicmvvm.home.domain.GetHistoriaDiariaUseCase
import javax.inject.Inject
@HiltViewModel
class AlbunesViewModel @Inject constructor(private val getAlbunesCase: GetAlbunesCase) : ViewModel()  {

    private val _albunesResponse = MutableLiveData<List<AlbunesResponse>>()
    val albunesResponse: LiveData<List<AlbunesResponse>> = _albunesResponse

    init {
        listarPhotos()
    }

    fun listarPhotos() {
        viewModelScope.launch {
            val response = getAlbunesCase()
            val filteredResponse = response.filter { it.albumId % 2 == 0 }
            _albunesResponse.value = filteredResponse
        }
    }

}