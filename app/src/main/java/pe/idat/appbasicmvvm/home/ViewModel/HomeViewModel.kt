package pe.idat.appbasicmvvm.home.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.appbasicmvvm.home.domain.GetHistoriaDiariaUseCase
import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val gethistoriadiariaUseCase: GetHistoriaDiariaUseCase) : ViewModel() {

    private val _historiadiariaResponse = MutableLiveData<List<HistoriaDiariaResponse>>()
    val historiadiariaResponse: LiveData<List<HistoriaDiariaResponse>> = _historiadiariaResponse

    init {
        listarHds()
    }

    fun listarHds() {
        viewModelScope.launch {
            val response = gethistoriadiariaUseCase()
            _historiadiariaResponse.value = response
            Log.i("POSTLIST", gethistoriadiariaUseCase().toString())
        }
    }

}