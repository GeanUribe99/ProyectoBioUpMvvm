package pe.idat.appbasicmvvm.auth


import androidx.lifecycle.LiveData
import pe.idat.appbasicmvvm.core.bd.PacienteDAO
import pe.idat.appbasicmvvm.core.bd.PacienteEntity
import javax.inject.Inject

class PacienteRepository @Inject constructor(
    private val pacienteDao: PacienteDAO
) {
    suspend fun  insertarPacientes(pacienteEntity: PacienteEntity){
        pacienteDao.insertarPaciente(pacienteEntity)
    }

    fun  obtenerpacientes(): LiveData<PacienteEntity> {
        return pacienteDao.obtenerPaciente()
    }


}