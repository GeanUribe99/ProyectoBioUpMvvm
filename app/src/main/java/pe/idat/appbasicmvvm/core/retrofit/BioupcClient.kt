package pe.idat.appbasicmvvm.core.retrofit

import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import retrofit2.Response
import retrofit2.http.GET

interface BioupcClient {

    @GET("/posts")
    //@GET("/eS0uhn/historiadiaria")
    suspend fun listarHds() : Response<List<HistoriaDiariaResponse>>
}