package pe.idat.appbasicmvvm.core.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitcModule {
    //HISTORIAS DIARIAS
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            //.baseUrl("https://retoolapi.dev/eS0uhn/historiadiaria/")
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBioupcClient(retrofit: Retrofit): BioupcClient {
        return retrofit.create(BioupcClient::class.java)
    }
    /*
    //INFO USUARIO

    @Singleton
    @Provides
    fun provideRetrofitIU(): Retrofit {
        return Retrofit.Builder()
            //.baseUrl("https://retoolapi.dev/eS0uhn/historiadiaria/")
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBioupcClientIU(retrofit: Retrofit): BioupcClient {
        return retrofit.create(BioupcClient::class.java)
    }

    //MAPAS
    @Singleton
    @Provides
    fun provideRetrofitMapas(): Retrofit {
        return Retrofit.Builder()
            //.baseUrl("https://retoolapi.dev/eS0uhn/historiadiaria/")
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBioupcClientMapas(retrofit: Retrofit): BioupcClient {
        return retrofit.create(BioupcClient::class.java)
    }

    //HISTORIA CLINICA

    @Singleton
    @Provides
    fun provideRetrofitHC(): Retrofit {
        return Retrofit.Builder()
            //.baseUrl("https://retoolapi.dev/eS0uhn/historiadiaria/")
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBioupcClientHC(retrofit: Retrofit): BioupcClient {
        return retrofit.create(BioupcClient::class.java)
    }
    */



}