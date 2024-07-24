package gabriela.marcos.bloom

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
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

class login : AppCompatActivity() {
    companion object variablesGlobalesRecuperacionDeContrasena {
        lateinit var correoLogin: String
        lateinit var IdEnfermera: String
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Mando a llamar a todos los elementos en pantalla

        val txtCorreoLogin = findViewById<EditText>(R.id.txtCorreoLogin)
        val txtContrasenaLogin = findViewById<EditText>(R.id.txtContrasenaLogin)
        val btnLogin = findViewById<ImageButton>(R.id.btnLogin)
        val btnRegister = findViewById<ImageButton>(R.id.btnRegister)

        //Creo la funcion para registrarse
        btnLogin.setOnClickListener {
            correoLogin = txtCorreoLogin.text.toString()
            //obtener id enfermera
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val objConexion = claseConexion().cadenaConexion()

                    // Preparar la consulta para obtener Id
                    val resultSet =
                        objConexion?.prepareStatement("SELECT idEnfermera FROM enfermera WHERE correoEnfermera = ?")
                    resultSet?.setString(1, correoLogin)

                    // Ejecutar la consulta y obtener el resultado
                    val resultado = resultSet?.executeQuery()

                    // Verificar si se encontró un resultado
                    if (resultado?.next() == true) {
                        IdEnfermera = resultado.getString("IdEmpleador")
                        // Ahora Id tiene el valor obtenido de la base de datos
                    } else {
                        // Manejar caso donde no se encontró Id (correo no existe)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@login, "Correo no encontrado", Toast.LENGTH_SHORT)
                                .show()
                        }
                        return@launch  // Salir del bloque de código si no se encontró el correo
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@login,
                            "Error al consultar la base de datos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            val Enfermeras = Intent(this, Enfermeras::class.java )
            GlobalScope.launch(Dispatchers.IO){
                val objConexion = claseConexion().cadenaConexion()
                val comprobarUsuario = objConexion?.prepareStatement("SELECT * FROM enfermera WHERE correoEnfermera = ? AND contrasena = ?")!!
                comprobarUsuario.setString(1, txtCorreoLogin.text.toString())
                comprobarUsuario.setString(2, txtContrasenaLogin.text.toString())
                val resultado = comprobarUsuario.executeQuery()
                if (resultado.next()){
                    startActivity(Enfermeras)
                } else{
                    println("Usuario no encontrado, verifique sus credenciales :(")
                }


            }
        }

        //Creo la funcion para llevar a la pantalla de registro
        btnRegister.setOnClickListener {
            val Register = Intent (this@login, registrarse::class.java)
            startActivity(Register)
        }
    }
}