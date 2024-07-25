package gabriela.marcos.bloom.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import gabriela.marcos.bloom.R
import gabriela.marcos.bloom.databinding.FragmentNotificationsBinding
import gabriela.marcos.bloom.login

class NotificationsFragment : Fragment() {

    private lateinit var textViewNombre: TextView
    private lateinit var textViewCorreo: TextView
    private lateinit var textViewNumero: TextView




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        textViewNombre = root.findViewById(R.id.textViewNombre)
        textViewCorreo = root.findViewById(R.id.textViewCorreo)
        textViewNumero = root.findViewById(R.id.textViewNumero)

        cargarDatos()
        return root

    }
    private fun cargarDatos(){
        textViewNombre.text = login.nombreEnfermera
        textViewCorreo.text = login.correoLogin
        textViewNumero.text = login.numeroEnfermera
    }


}