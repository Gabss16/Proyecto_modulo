package gabriela.marcos.bloom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import gabriela.marcos.bloom.ui.home.HomeFragment.Companion.idPaciente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelo.claseConexion

class Editarpaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editarpaciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Mando a llamar a todos loselementos en pantalla

        val txtNombrePacienteEdit = findViewById<EditText>(R.id.txtNombrePacienteEdit)
        val txtApellidoPacienteEdit = findViewById<EditText>(R.id.txtApellidoPacienteEdit)
        val txtEdadPacienteEdit = findViewById<EditText>(R.id.txtEdadPacienteEdit)
        val txtEnfermedadPacienteEdit = findViewById<EditText>(R.id.txtEnfermedadPacienteEdit)
        val txtNumeroHabitacionPacienteEdit = findViewById<EditText>(R.id.txtNumeroHabitacionEdit)
        val txtNumeroCamaEdit = findViewById<EditText>(R.id.txtNumeroCamaEdit)
        val txtMedicamentoEdit = findViewById<EditText>(R.id.txtMedicamentoAsignadoEdit)
        val txtFechaIngresoEdit = findViewById<EditText>(R.id.txtFechaIngresoEdit)
        val txtHoraDeAplicacionEdit = findViewById<EditText>(R.id.txtHoraDeAplicacion)
        val btnEdit = findViewById<Button>(R.id.btnEditar)


        //Obtener valores de los edit text

        val nombreEdit =txtNombrePacienteEdit.text.toString()
        val apellidoEdit = txtApellidoPacienteEdit.text.toString()
        val edadEdit = txtEdadPacienteEdit.text.toString()
        val enfermedadEdit = txtEnfermedadPacienteEdit.text.toString()
        val numeroHabitacionEdit = txtNumeroHabitacionPacienteEdit.text.toString()
        val numeroCamaEdit = txtNumeroCamaEdit.text.toString()
        val MedicamentoEdit = txtMedicamentoEdit.text.toString()
        val fechaIngresoEdit = txtFechaIngresoEdit.text.toString()
        val horaAplicacionEdit = txtHoraDeAplicacionEdit.text.toString()

        btnEdit.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
            val objConexion = claseConexion().cadenaConexion()
            val actualizarPaciente = objConexion?.prepareStatement(
            "UPDATE paciente SET nombresPaciente = ?, apellidosPaciente =?, edad=?, enfermedad=?, numeroHabitacion=?, numeroCama=?, medicamentosAsignados=?, fechaIngreso=?, horaDeAplicacionDeMedicamentos=? WHERE idPaciente=?")!!
            actualizarPaciente.setString(1, nombreEdit)
            actualizarPaciente.setString(2, apellidoEdit)
            actualizarPaciente.setString(3, edadEdit)
            actualizarPaciente.setString(4, enfermedadEdit)
            actualizarPaciente.setString(5, numeroHabitacionEdit)
            actualizarPaciente.setString(6, numeroCamaEdit)
            actualizarPaciente.setString(7, MedicamentoEdit)
            actualizarPaciente.setString(8, fechaIngresoEdit)
            actualizarPaciente.setString(9, horaAplicacionEdit)
            actualizarPaciente.setString(10, idPaciente)


                val filasAfectadas = actualizarPaciente.executeUpdate()
            }

        }


    }
}