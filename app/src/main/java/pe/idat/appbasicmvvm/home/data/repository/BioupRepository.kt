package pe.idat.appbasicmvvm.home.data.repository

import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import pe.idat.appbasicmvvm.home.data.network.service.BioupService
import javax.inject.Inject

class BioupRepository @Inject constructor(private val bioupService: BioupService) {
    suspend fun listarHds(): List<HistoriaDiariaResponse> {
        return bioupService.listarHds()
    }
}