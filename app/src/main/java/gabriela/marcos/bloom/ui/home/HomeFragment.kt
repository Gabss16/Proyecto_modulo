package gabriela.marcos.bloom.ui.home

import RecicleViewHelpers.Adaptador
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import gabriela.marcos.bloom.R
import gabriela.marcos.bloom.databinding.FragmentHomeBinding
import gabriela.marcos.bloom.login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.Paciente
import modelo.claseConexion
import java.util.UUID

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        //codigo
        // initializing our variable for button with its id.
        val btnShowBottomSheet = root.findViewById<ImageButton>(R.id.idBtnShowBottomSheet);
        val rcvPacientes = root.findViewById<RecyclerView>(R.id.rcvPacientes)

        rcvPacientes.layoutManager = LinearLayoutManager(requireContext())

        fun obtenerDatosPacientes(): List<Paciente> {
            //1- Creo un objeto de la clase conexión
            val objConexion = claseConexion().cadenaConexion()

            //2 - Obtengo el ID de la enfermera que inició sesión
            fun obtenerIdEnfermera(): String {
                return login.variablesGlobalesRecuperacionDeContrasena.IdEnfermera
            }
            val idEnfermera = obtenerIdEnfermera()

            // El símbolo de pregunta es porque los datos pueden ser nulos
            val statement = objConexion?.prepareStatement("SELECT * FROM paciente WHERE idEnfermera = ?")
            statement?.setString(1, idEnfermera)
            val resultSet = statement?.executeQuery()!!

            // En esta variable se añaden TODOS los valores de pacientes
            val listaPacientes = mutableListOf<Paciente>()

            // Recorro todos los registros de la base de datos
            // .next() significa que mientras haya un valor después de ese se va a repetir el proceso
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
                rcvPacientes.adapter = adapter
            }
        }

        // adding on click listener for our button.
        btnShowBottomSheet.setOnClickListener {

            // on below line we are creating a new bottom sheet dialog.
            val dialog = BottomSheetDialog(requireContext())

            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

            // on below line we are creating a variable for our button
            // which we are using to dismiss our dialog.
            val btnClose = view.findViewById<ImageButton>(R.id.idBtnDismiss)
            val txtnombreP = view.findViewById<EditText>(R.id.txtNombreP)
            val txtapellidoP = view.findViewById<EditText>(R.id.txtApellidoP)
            val txtEdadP = view.findViewById<EditText>(R.id.txtEdadP)
            val txtEnfermedadP = view.findViewById<EditText>(R.id.txtEnfermedadP)
            val txtHabP = view.findViewById<EditText>(R.id.txtHabP)
            val txtCamaP = view.findViewById<EditText>(R.id.txtCamaP)
            val txtMedicamentosP = view.findViewById<EditText>(R.id.txtMedicamentosP)
            val txtIngresoP = view.findViewById<EditText>(R.id.txtIngresoP)
            val txtHoraMedicina = view.findViewById<EditText>(R.id.txtHoraMedicina)

            fun obtenerIdEnfermera(): String {
                return login.variablesGlobalesRecuperacionDeContrasena.IdEnfermera
            }

            val idEnfermera = obtenerIdEnfermera()
            Log.d("InsertJob", "IdEnfermera obtenido: $idEnfermera")

            // on below line we are adding on click listener
            // for our dismissing the dialog button.
            btnClose.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val objConexion = claseConexion().cadenaConexion()
                        val addPaciente = objConexion?.prepareStatement(
                            "INSERT INTO PACIENTE (idPaciente, idEnfermera, nombresPaciente, apellidosPaciente, edad, enfermedad, numeroHabitacion, numeroCama, medicamentosAsignados, fechaIngreso, horaDeAplicacionDeMedicamentos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                        )!!

                        addPaciente.setString(1, UUID.randomUUID().toString())
                        addPaciente.setString(2, idEnfermera)
                        addPaciente.setString(3, txtnombreP.text.toString())
                        addPaciente.setString(4, txtapellidoP.text.toString())
                        addPaciente.setInt(5, txtEdadP.text.toString().toInt())
                        addPaciente.setString(6, txtEnfermedadP.text.toString())
                        addPaciente.setInt(7, txtHabP.text.toString().toInt())
                        addPaciente.setInt(8, txtCamaP.text.toString().toInt())
                        addPaciente.setString(9, txtMedicamentosP.text.toString())
                        addPaciente.setString(10, txtIngresoP.text.toString())
                        addPaciente.setString(11, txtHoraMedicina.text.toString())

                        Log.d(
                            "InsertPaciente",
                            "Datos a insertar: idEnfermera=$idEnfermera, nombres=${txtnombreP.text}, apellidos=${txtapellidoP.text}, edad=${txtEdadP.text}, enfermedad=${txtEnfermedadP.text}, numeroHabitacion=${txtHabP.text}, numeroCama=${txtCamaP.text}, medicamentosAsignados=${txtMedicamentosP.text}, fechaIngreso=${txtIngresoP.text}, horaDeAplicacionDeMedicamentos=${txtHoraMedicina.text}"
                        )

                        addPaciente.executeUpdate()
                        val pacienteDB = obtenerDatosPacientes()

                        withContext(Dispatchers.Main) {
                            (rcvPacientes.adapter as? Adaptador)?.actualizarDatos(pacienteDB)
                            Toast.makeText(requireContext(), "Paciente Ingresado", Toast.LENGTH_LONG).show()
                            dialog.dismiss()
                        }
                    } catch (e: Exception) {
                        Log.e("InsertPaciente", "Error al insertar paciente", e)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Error al insertar paciente", Toast.LENGTH_LONG).show()
                        }
                    }
                }


                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(false)

            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)

            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}