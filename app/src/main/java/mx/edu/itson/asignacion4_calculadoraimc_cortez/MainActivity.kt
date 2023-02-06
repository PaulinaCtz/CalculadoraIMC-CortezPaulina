package mx.edu.itson.asignacion4_calculadoraimc_cortez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaración de variables
        var txtResultado: TextView = findViewById(R.id.imc)
        var txtEstado: TextView = findViewById(R.id.range)


        val btnCalcula: Button = findViewById(R.id.button)

        btnCalcula.setOnClickListener{
            //Declaración de los editText
            val etEstatura: EditText = findViewById(R.id.height)
            val etPeso: EditText = findViewById(R.id.weight)
            //Convertir los kilos y estatura a double.
            var peso: Double = 0.0
            var estatura: Double = 0.0

            try {
                peso = etPeso.text.toString().toDouble()
                estatura = etEstatura.text.toString().toDouble()
            } catch(e: java.lang.Exception){
                txtResultado.setText("Debe ingresar valores reales")
                println(e)
            }

            var resultado = calculaIMC(estatura, peso)
            val formattedNumber = "%.2f".format(resultado)
            txtResultado.setText(formattedNumber)

            var salud: String
            var color: Int

            when {
                resultado < 18.5 -> {
                    salud = "Bajo Peso"
                    color = R.color.colorRed
                }

                resultado >= 18.5 && resultado <= 24.9 -> {
                    salud = "Saludable"
                    color = R.color.colorGreenish
                }

                resultado >= 25 && resultado <= 29.9 -> {
                    salud =  "Sobrepeso"
                    color = R.color.colorYellow
                }

                resultado >= 30 && resultado <= 34.9 -> {
                    salud = "Obesidad Grado 1"
                    color = R.color.colorOrange
                }

                resultado >= 35 && resultado <= 39.9 -> {
                    salud = "Obesidad Grado 2"
                    color = R.color.colorBrown
                }

                resultado >= 40 -> {
                    salud = "Obesidad Grado 3"
                    color = R.color.colorRed
                }

                else -> {
                    salud = "Error"
                    color = 0
                }
            }

            txtEstado.setBackgroundResource(color)
            txtEstado.setText(salud)
        }
    }

    /**
     * Función encargada de calcular el imc en base la altura y peso del
     * usuario.
     */
    fun calculaIMC(altura: Double, peso: Double): Double{
        val imc: Double = (peso/(Math.pow(altura, 2.0)))
        return imc
    }
}