package br.edu.ifsp.scl.sc3044025.trucoscoreboard

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sc3044025.trucoscoreboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var pontosA = 0
    private var pontosB = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.addOneA.setOnClickListener {
            pontosA++
            verificarVencedor()
            amb.numberA.text = pontosA.toString()
        }

        amb.addOneB.setOnClickListener {
            pontosB++
            verificarVencedor()
            amb.numberB.text = pontosB.toString()
        }

        amb.trucoA.setOnClickListener {
            pontosA = (pontosA + 3).coerceAtMost(12)
            verificarVencedor()
            amb.numberA.text = pontosA.toString()
        }

        amb.trucoB.setOnClickListener {
            pontosB = (pontosB + 3).coerceAtMost(12)
            verificarVencedor()
            amb.numberB.text = pontosB.toString()
        }

        amb.resetGame.setOnClickListener {
            reiniciarJogo()
        }

    }

    private fun verificarVencedor() {
        if (pontosA >= 12) {
            mostrarDialogo("EQUIPE A VENCEU!")
        } else if (pontosB >= 12) {
            mostrarDialogo("EQUIPE B VENCEU!")
        }
        if(pontosA == 11){
            amb.trucoA.isEnabled = false
            amb.trucoA.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
        }
        if(pontosB == 11){
            amb.trucoB.isEnabled = false
            amb.trucoB.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
        }
    }

    private fun mostrarDialogo(mensagem: String) {

        val builder = androidx.appcompat.app.AlertDialog.Builder(this)

        builder.setTitle("Fim de Jogo")
        builder.setMessage(mensagem)

        builder.setPositiveButton("Reiniciar") { _, _ ->
            reiniciarJogo()
        }

        builder.setNegativeButton("Fechar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setCancelable(false)

        val dialog = builder.create()
        dialog.show()
    }

    private fun reiniciarJogo() {
        pontosA = 0
        pontosB = 0

        amb.numberA.text = "0"
        amb.numberB.text = "0"

        amb.addOneA.isEnabled = true
        amb.addOneB.isEnabled = true
        amb.trucoA.isEnabled = true
        amb.trucoB.isEnabled = true


        amb.trucoB.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#d32f2f"))


        amb.trucoA.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#d32f2f"))
    }
}