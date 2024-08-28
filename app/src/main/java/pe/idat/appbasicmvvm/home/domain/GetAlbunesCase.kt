package pe.idat.appbasicmvvm.home.domain

import pe.idat.appbasicmvvm.home.data.network.response.AlbunesResponse
import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import pe.idat.appbasicmvvm.home.data.repository.BioupRepository
import javax.inject.Inject

class GetAlbunesCase@Inject constructor(private val bioupRepository: BioupRepository) {
    suspend operator fun invoke():List<AlbunesResponse>{
        return bioupRepository.listarphotos()
    }
}