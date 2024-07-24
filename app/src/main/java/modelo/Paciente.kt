package modelo

data class Paciente(
    val idPaciente: String,
    val idEnfermera: String,
    val nombresPaciente: String,
    val apellidosPaciente: String,
    val edad: Int,
    val enfermedad: String,
    val numeroHabitacion: Int,
    val numeroCama: Int,
    val medicamentosAsignados: String,
    val fechaIngreso: String,
    val horaDeAplicacionDeMedicamentos: String,

)
