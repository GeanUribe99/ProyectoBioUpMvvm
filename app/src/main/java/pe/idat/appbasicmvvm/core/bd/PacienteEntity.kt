package pe.idat.appbasicmvvm.core.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paciente")
data class PacienteEntity(
    @PrimaryKey
    val codPaciente: Int,
    val password: String,
    val nombreapellido: String,
    val celular: String
)
