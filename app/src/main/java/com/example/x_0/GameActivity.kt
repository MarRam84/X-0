package com.example.x_0

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameActivity : AppCompatActivity() {
    lateinit var bt1: Button
    lateinit var bt2: Button
    lateinit var bt3: Button
    lateinit var bt4: Button
    lateinit var bt5: Button
    lateinit var bt6: Button
    lateinit var bt7: Button
    lateinit var bt8: Button
    lateinit var bt9: Button
    lateinit var tvPlayer1: TextView
    lateinit var tvPlayer2: TextView
    lateinit var tvscoreplayer1: TextView
    lateinit var tvscoreplayer2: TextView

    var currentPlayer: Int = 1
    var scorePlayer1: Int = 0
    var scorePlayer2: Int = 0
    var gameFinished: Boolean = false
    var lastWinner: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
    }

    private fun initUI() {
        bt1 = findViewById(R.id.bt1)
        bt2 = findViewById(R.id.bt2)
        bt3 = findViewById(R.id.bt3)
        bt4 = findViewById(R.id.bt4)
        bt5 = findViewById(R.id.bt5)
        bt6 = findViewById(R.id.bt6)
        bt7 = findViewById(R.id.bt7)
        bt8 = findViewById(R.id.bt8)
        bt9 = findViewById(R.id.bt9)
        tvPlayer1 = findViewById(R.id.tvPlayer1)
        tvPlayer2 = findViewById(R.id.tvPlayer2)
        tvscoreplayer1 = findViewById(R.id.tvscoreplayer1)
        tvscoreplayer2 = findViewById(R.id.tvscoreplayer2)

        tvPlayer1.text = intent?.getStringExtra("player1") ?: "Jugador 1"
        tvPlayer2.text = intent?.getStringExtra("player2") ?: "Jugador 2"
        nuevapartida()
    }

    fun play(btn: View) {
        val mybtn: Button = btn as Button
        if (!gameFinished && mybtn.text.isBlank()) {
            mybtn.text = if (currentPlayer == 1) "X" else "O"

            val winningButtons = validateCards()
            if (winningButtons.isNotEmpty()) {
                if (currentPlayer == 1) {
                    scorePlayer1++
                    tvscoreplayer1.text = "$scorePlayer1"
                    Toast.makeText(applicationContext, "${tvPlayer1.text} Ha Ganado", Toast.LENGTH_LONG).show()
                } else {
                    scorePlayer2++
                    tvscoreplayer2.text = "$scorePlayer2"
                    Toast.makeText(applicationContext, "${tvPlayer2.text} Ha Ganado", Toast.LENGTH_LONG).show()
                }
                lastWinner = currentPlayer
                gameFinished = true
                resaltarGanador(winningButtons)
            } else if (validateDraw()) {
                Toast.makeText(applicationContext, "Empate", Toast.LENGTH_LONG).show()
                lastWinner = 1
                gameFinished = true
            } else {
                cambiarTurno()
            }
        }
    }

    private fun validateCards(): List<Button> {
        val board = listOf(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9)
        val winPatterns = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )

        for (pattern in winPatterns) {
            val (a, b, c) = pattern
            val va = board[a].text.toString()
            val vb = board[b].text.toString()
            val vc = board[c].text.toString()
            if (va.isNotBlank() && va == vb && va == vc) {
                return listOf(board[a], board[b], board[c])
            }
        }
        return emptyList()
    }

    private fun resaltarGanador(botones: List<Button>) {
        val colorGanador = if (currentPlayer == 1) {
            Color.rgb(255, 215, 0) // Dorado para jugador 1
        } else {
            Color.rgb(135, 206, 250) // Azul claro para jugador 2
        }

        for (btn in botones) {
            btn.setBackgroundColor(colorGanador)

            val anim = ScaleAnimation(
                1f, 1.2f, 1f, 1.2f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
            )
            anim.duration = 500
            anim.repeatCount = 6
            anim.repeatMode = ScaleAnimation.REVERSE
            btn.startAnimation(anim)
        }
    }

    private fun validateDraw(): Boolean {
        return listOf(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9).all {
            it.text.toString().isNotBlank()
        }
    }

    private fun cambiarTurno() {
        currentPlayer = if (currentPlayer == 1) 2 else 1
        actualizarColores()
    }

    private fun actualizarColores() {
        tvPlayer1.setTextColor(if (currentPlayer == 1) Color.MAGENTA else Color.LTGRAY)
        tvPlayer2.setTextColor(if (currentPlayer == 2) Color.MAGENTA else Color.LTGRAY)
    }

    fun nuevapartida(view: View? = null) {
        listOf(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9).forEach {
            it.text = ""
            it.setBackgroundColor(Color.TRANSPARENT)
        }
        gameFinished = false
        currentPlayer = lastWinner
        actualizarColores()
    }
}