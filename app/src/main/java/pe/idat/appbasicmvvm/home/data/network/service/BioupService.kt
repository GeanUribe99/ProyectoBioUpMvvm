package pe.idat.appbasicmvvm.home.data.network.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.idat.appbasicmvvm.core.retrofit.BioupcClient
import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import javax.inject.Inject

class BioupService @Inject constructor(private val bioupcClient: BioupcClient) {

    suspend fun listarHds(): List<HistoriaDiariaResponse> {
        return withContext(Dispatchers.IO){
            val response = bioupcClient.listarHds()
            response.body()!!
        }
    }
}
