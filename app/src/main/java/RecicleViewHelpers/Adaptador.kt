package RecicleViewHelpers

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gabriela.marcos.bloom.R
import gabriela.marcos.bloom.detallePaciente
import modelo.Paciente

class Adaptador(var Datos : List<Paciente>) : RecyclerView.Adapter<ViewHolder>(){

    fun actualizarDatos(nuevosDatos: List<Paciente>) {
        Datos= nuevosDatos
        notifyDataSetChanged()}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() =Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paciente = Datos[position]
        holder.txtNombrePaciente.text = paciente.nombresPaciente
        holder.txtEnfermedad.text = paciente.enfermedad

        //click a la card
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val detalle = Intent(context, detallePaciente::class.java).apply {
                putExtra("ID_PACIENTE", paciente.idPaciente)
                putExtra("NOMBRES_PACIENTE", paciente.nombresPaciente)
                putExtra("APELLIDOS_PACIENTE", paciente.apellidosPaciente)
                putExtra("EDAD", paciente.edad)
                putExtra("ENFERMEDAD", paciente.enfermedad)
                putExtra("NUMERO_HABITACION", paciente.numeroHabitacion)
                putExtra("NUMERO_CAMA", paciente.numeroCama)
                putExtra("MEDICAMENTOS_ASIGNADOS", paciente.medicamentosAsignados)
                putExtra("FECHA_INGRESO", paciente.fechaIngreso)
                putExtra("HORA_APLICACION_MEDICAMENTOS", paciente.horaDeAplicacionDeMedicamentos)
            }
            context.startActivity(detalle)


    }
} }