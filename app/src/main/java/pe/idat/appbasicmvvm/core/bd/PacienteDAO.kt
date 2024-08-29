package pe.idat.appbasicmvvm.core.bd
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface PacienteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPaciente(vararg paciente: PacienteEntity)

    @Query("SELECT * FROM paciente LIMIT 1")
    fun obtenerPaciente(): LiveData<PacienteEntity>
}