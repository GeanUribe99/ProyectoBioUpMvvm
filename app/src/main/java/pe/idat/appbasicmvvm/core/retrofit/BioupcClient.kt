package pe.idat.appbasicmvvm.core.retrofit

import pe.idat.appbasicmvvm.home.data.network.response.AlbunesResponse
import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import retrofit2.Response
import retrofit2.http.GET

interface BioupcClient {

    @GET("/todos")
    suspend fun listarHds() : Response<List<HistoriaDiariaResponse>>

    @GET("/photos")
    suspend fun listarphotos(): Response<List<AlbunesResponse>>
}