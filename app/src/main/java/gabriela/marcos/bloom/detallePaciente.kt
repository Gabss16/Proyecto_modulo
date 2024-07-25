package gabriela.marcos.bloom

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.claseConexion

class detallePaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        var idPaciente = intent.getStringExtra("ID_PACIENTE")
        val nombresPaciente = intent.getStringExtra("NOMBRES_PACIENTE")
        val apellidosPaciente = intent.getStringExtra("APELLIDOS_PACIENTE")
        val edad = intent.getIntExtra("EDAD", 0)
        val enfermedad = intent.getStringExtra("ENFERMEDAD")
        val numeroHabitacion = intent.getIntExtra("NUMERO_HABITACION", 0)
        val numeroCama = intent.getIntExtra("NUMERO_CAMA", 0)
        val medicamentosAsignados = intent.getStringExtra("MEDICAMENTOS_ASIGNADOS")
        val fechaIngreso = intent.getStringExtra("FECHA_INGRESO")
        val horaAplicacionMedicamentos = intent.getStringExtra("HORA_APLICACION_MEDICAMENTOS")

        idPaciente = intent.getStringExtra("ID_PACIENTE") ?: return

        val txtNombresPaciente = findViewById<TextView>(R.id.txtNombresPaciente)
        val txtApellidosPaciente = findViewById<TextView>(R.id.txtApellidosPaciente)
        val txtEdad = findViewById<TextView>(R.id.txtEdad)
        val txtEnfermedad = findViewById<TextView>(R.id.txtEnfermedadd)
        val txtNumeroHabitacion = findViewById<TextView>(R.id.txtNumeroHabitacion)
        val txtNumeroCama = findViewById<TextView>(R.id.txtNumeroCama)
        val txtMedicamentosAsignados = findViewById<TextView>(R.id.txtMedicamentosAsignados)
        val txtFechaIngreso = findViewById<TextView>(R.id.txtFechaIngreso)
        val txtHoraAplicacionMedicamentos = findViewById<TextView>(R.id.txtHoraAplicacionMedicamentos)

        txtNombresPaciente.text = nombresPaciente
        txtApellidosPaciente.text = apellidosPaciente
        txtEdad.text = edad.toString()
        txtEnfermedad.text = enfermedad
        txtNumeroHabitacion.text = numeroHabitacion.toString()
        txtNumeroCama.text = numeroCama.toString()
        txtMedicamentosAsignados.text = medicamentosAsignados
        txtFechaIngreso.text = fechaIngreso
        txtHoraAplicacionMedicamentos.text = horaAplicacionMedicamentos

        val btnEliminarPaciente = findViewById<ImageButton>(R.id.btnEliminarPaciente)
        btnEliminarPaciente.setOnClickListener {
            eliminarPaciente(idPaciente)
        }
    }

    private fun eliminarPaciente(idPaciente: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val objConexion = claseConexion().cadenaConexion()
                val eliminarStmt = objConexion?.prepareStatement("DELETE FROM paciente WHERE idPaciente = ?")
                eliminarStmt?.setString(1, idPaciente)

                val rowsAffected = eliminarStmt?.executeUpdate()
                withContext(Dispatchers.Main) {
                    if (rowsAffected != null && rowsAffected > 0) {
                        Toast.makeText(this@detallePaciente, "Paciente eliminado con éxito", Toast.LENGTH_LONG).show()
                        // Volver a la pantalla anterior o hacer algo después de la eliminación
                        finish()
                    } else {
                        Toast.makeText(this@detallePaciente, "Error al eliminar el paciente", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@detallePaciente, "Error al eliminar el paciente", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}