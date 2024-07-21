package gabriela.marcos.bloom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
import java.util.UUID

class registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Mando a llamar a todos los elementos en pantalla

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreo)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasena)
        val txtTelefono = findViewById<EditText>(R.id.txtTelefono)
        val btnregistrarse = findViewById<ImageButton>(R.id.btnRegistrarse)

        //Hago la funcion de registro
        btnregistrarse.setOnClickListener {
            val Login = Intent (this, login::class.java)
            GlobalScope.launch(Dispatchers.IO){
                val objConexion = claseConexion().cadenaConexion()

                val registrarUsuario = objConexion?.prepareStatement("Insert into enfermera(idEnfermera, nombreEnfermera, contrasena, correoEnfermera, telefonoEnfermera) VALUES (?,?,?,?,?)")!!
                registrarUsuario.setString(1, UUID.randomUUID().toString())
                registrarUsuario.setString(2, txtNombre.text.toString())
                registrarUsuario.setString(3, txtContrasena.text.toString())
                registrarUsuario.setString(4, txtCorreo.text.toString())
                registrarUsuario.setString(5, txtTelefono.text.toString())
                registrarUsuario.executeUpdate()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@registrarse, "Usuario creado", Toast.LENGTH_SHORT).show()
                    startActivity(Login)
                }


            }
        }
    }
}
    }
}