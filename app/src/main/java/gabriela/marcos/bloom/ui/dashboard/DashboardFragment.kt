package gabriela.marcos.bloom.ui.dashboard

import RecicleViewHelpers.Adaptador
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gabriela.marcos.bloom.R
import gabriela.marcos.bloom.databinding.FragmentDashboardBinding
import gabriela.marcos.bloom.login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.Paciente
import modelo.claseConexion

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rcvPacientes2 = root.findViewById<RecyclerView>(R.id.rcvPacientes2)
        rcvPacientes2.layoutManager = LinearLayoutManager(requireContext())

        fun obtenerDatosPacientes(): List<Paciente> {
            val objConexion = claseConexion().cadenaConexion()
            val idEnfermera = login.variablesGlobalesRecuperacionDeContrasena.IdEnfermera

            // Verifica el nombre de la columna
            val statement = objConexion?.prepareStatement("SELECT * FROM paciente ")
            statement?.setString(1, idEnfermera)
            val resultSet = statement?.executeQuery()!!

            val listaPacientes = mutableListOf<Paciente>()

            while (resultSet.next()) {
                val idPaciente = resultSet.getString("idPaciente")
                val nombresPaciente = resultSet.getString("nombresPaciente")
                val apellidosPaciente = resultSet.getString("apellidosPaciente")
                val edad = resultSet.getInt("edad")
                val enfermedad = resultSet.getString("enfermedad")
                val numeroHabitacion = resultSet.getInt("numeroHabitacion")
                val numeroCama = resultSet.getInt("numeroCama")
                val medicamentosAsignados = resultSet.getString("medicamentosAsignados")
                val fechaIngreso = resultSet.getString("fechaIngreso")
                val horaDeAplicacionDeMedicamentos = resultSet.getString("horaDeAplicacionDeMedicamentos")

                val paciente = Paciente(
                    idPaciente,
                    idEnfermera,
                    nombresPaciente,
                    apellidosPaciente,
                    edad,
                    enfermedad,
                    numeroHabitacion,
                    numeroCama,
                    medicamentosAsignados,
                    fechaIngreso,
                    horaDeAplicacionDeMedicamentos
                )
                listaPacientes.add(paciente)
            }
            return listaPacientes
        }

        CoroutineScope(Dispatchers.IO).launch {
            val pacienteDB = obtenerDatosPacientes()
            withContext(Dispatchers.Main) {
                val adapter = Adaptador(pacienteDB)
                rcvPacientes2.adapter = adapter
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}