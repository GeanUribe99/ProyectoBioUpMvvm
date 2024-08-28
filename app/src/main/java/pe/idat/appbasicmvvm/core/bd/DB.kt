package pe.idat.appbasicmvvm.core.bd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PacienteEntity::class],
    version = 1
)

abstract class DB:RoomDatabase() {
    abstract fun pacienteDao():PacienteDAO
}