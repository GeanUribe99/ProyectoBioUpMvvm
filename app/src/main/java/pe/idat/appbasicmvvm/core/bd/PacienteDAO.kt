package pe.idat.appbasicmvvm.core.bd
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface PacienteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPaciente(vararg paciente: PacienteEntity)
}