package pe.idat.appbasicmvvm.core.bd

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DBPacienteModule {
    @Provides
    fun providePersonaDao(db: DB): PacienteDAO{
        return db.pacienteDao()
    }
    @Provides
    @Singleton
    fun providePatitasDatabase(@ApplicationContext context: Context): DB{
        return Room.databaseBuilder(context,
            DB::class.java,
            "pacientesdb1").build()

    }
}