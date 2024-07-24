package RecicleViewHelpers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gabriela.marcos.bloom.R

class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    var txtNombrePaciente= view.findViewById<TextView>(R.id.txtNombrePaciente)
    var txtEnfermedad =  view.findViewById<TextView>(R.id.txtEnfermedad)
}