package RecicleViewHelpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gabriela.marcos.bloom.R
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


    }
}