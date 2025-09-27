package com.example.x_0

import android.graphics.Color
import android.os.Bundle
import android.view.View
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
    var gameFinished: Boolean =false


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

        tvPlayer1.text = intent?.extras?.getString("player1").toString()
        tvPlayer2.text = intent?.extras?.getString("player2").toString()
        nuevapartida(this.tvPlayer1)
    }
    fun play(btn: View){
        val mybtn: Button= btn as Button
        if(!gameFinished && mybtn.text.toString().isEmpty()){
            if(currentPlayer == 1){
                mybtn.text = "X"
                validateWinner(btn)
                currentPlayer = 2
                tvPlayer1.setTextColor(Color.LTGRAY)
                tvPlayer2.setTextColor(Color.MAGENTA)
            }
            else{
                mybtn.text = "O"
                validateWinner(btn)
                currentPlayer = 1
                tvPlayer1.setTextColor(Color.MAGENTA)
                tvPlayer2.setTextColor(Color.LTGRAY)
            }
            if (!gameFinished && validateDraw()) {
                Toast.makeText(applicationContext, "Empate", Toast.LENGTH_LONG).show()
                gameFinished = true
            }


        }
    }
    fun validateWinner(btn: View){


        if (validateCards(btn)){
            if (currentPlayer == 1) {
                scorePlayer1++
                tvscoreplayer1.text = "$scorePlayer1"
                Toast.makeText(applicationContext, "${tvPlayer1.text} Ha Ganado", Toast.LENGTH_LONG)
                    .show()
            } else {
                scorePlayer2++
                tvscoreplayer2.text = "$scorePlayer2"
                Toast.makeText(applicationContext, "${tvPlayer2.text} Ha Ganado", Toast.LENGTH_LONG)
                    .show()
            }
            gameFinished = true

        }

    }

    private fun validateCards(btn: View): Boolean {
        val bt1Val = bt1.text.toString().trim()
        val bt2Val = bt2.text.toString().trim()
        val bt3Val = bt3.text.toString().trim()
        val bt4Val = bt4.text.toString().trim()
        val bt5Val = bt5.text.toString().trim()
        val bt6Val = bt6.text.toString().trim()
        val bt7Val = bt7.text.toString().trim()
        val bt8Val = bt8.text.toString().trim()
        val bt9Val = bt9.text.toString().trim()
        var winner = false

        when(btn.id){
            bt1.id->{
                if ((!bt1Val.isEmpty() &&
                            ((bt1Val == bt2Val) && (bt1Val == bt3Val)))
                    ||
                    ((bt1Val == bt5Val) && (bt1Val == bt9Val))
                    ||
                    ((bt1Val == bt4Val) && (bt1Val == bt7Val))
                ) {
                    winner = true
                }
            }
            bt2.id ->{
                if ((!bt2Val.isEmpty() &&
                            ((bt2Val == bt1Val) && (bt2Val == bt3Val)))
                    ||
                    ((bt2Val == bt5Val) && (bt2Val == bt8Val))
                ){
                    winner = true
                }
            }
            bt3.id -> {
                if ((!bt3Val.isEmpty() &&
                            ((bt3Val == bt1Val) && (bt3Val == bt2Val)))
                    ||
                    ((bt3Val == bt6Val) && (bt3Val == bt9Val))
                    ||
                    ((bt3Val == bt5Val) && (bt3Val == bt7Val))
                ) {
                    winner = true
                }
            }
            bt4.id -> {
                if ((!bt4Val.isEmpty() &&
                            ((bt4Val == bt1Val) && (bt4Val == bt7Val)))
                    ||
                    ((bt4Val == bt5Val) && (bt4Val == bt6Val))
                ) {
                    winner = true
                }
            }
            bt5.id -> if ((!bt5Val.isEmpty() &&
                        ((bt5Val == bt2Val) && (bt5Val == bt8Val)))
                ||
                ((bt5Val == bt4Val) && (bt5Val == bt6Val))
                ||
                ((bt5Val == bt1Val) && (bt5Val == bt9Val))
                ||
                ((bt5Val == bt3Val) && (bt5Val == bt7Val))
            ) {
                winner = true
            }
            bt6.id -> {
                if ((!bt6Val.isEmpty() &&
                            ((bt6Val == bt3Val) && (bt6Val == bt9Val)))
                    ||
                    ((bt6Val == bt4Val) && (bt6Val == bt5Val))
                ) {
                    winner = true
                }
            }
            bt7.id -> {
                if ((!bt7Val.isEmpty() &&
                            ((bt7Val == bt1Val) && (bt7Val == bt4Val)))
                    ||
                    ((bt7Val == bt5Val) && (bt7Val == bt3Val))
                    ||
                    ((bt7Val == bt8Val) && (bt7Val == bt9Val))
                ){
                    winner = true
                }
            }
            bt8.id -> {
                if ((!bt8Val.isEmpty() &&
                            ((bt8Val == bt7Val) && (bt8Val == bt9Val)))
                    ||
                    ((bt8Val == bt2Val) && (bt8Val == bt5Val))
                ) {
                    winner = true
                }
            } bt9.id -> {
            if ((!bt9Val.isEmpty() &&
                        ((bt9Val == bt3Val) && (bt9Val == bt6Val)))
                ||
                ((bt9Val == bt5Val) && (bt9Val == bt1Val))
                ||
                ((bt9Val == bt7Val) && (bt9Val == bt8Val))
            ) {
                winner = true
            }
        }
            else->{
                winner = false
            }

        }
        return winner
    }
    private fun validateDraw(): Boolean {
        val bt1Val = bt1.text.toString().trim()
        val bt2Val = bt2.text.toString().trim()
        val bt3Val = bt3.text.toString().trim()
        val bt4Val = bt4.text.toString().trim()
        val bt5Val = bt5.text.toString().trim()
        val bt6Val = bt6.text.toString().trim()
        val bt7Val = bt7.text.toString().trim()
        val bt8Val = bt8.text.toString().trim()
        val bt9Val = bt9.text.toString().trim()

        return bt1Val.isNotEmpty() &&
                bt2Val.isNotEmpty() &&
                bt3Val.isNotEmpty() &&
                bt4Val.isNotEmpty() &&
                bt5Val.isNotEmpty() &&
                bt6Val.isNotEmpty() &&
                bt7Val.isNotEmpty() &&
                bt8Val.isNotEmpty() &&
                bt9Val.isNotEmpty()
    }

    fun nuevapartida(view: View) {
        bt1.text=""
        bt2.text=""
        bt3.text=""
        bt4.text=""
        bt5.text=""
        bt6.text=""
        bt7.text=""
        bt8.text=""
        bt9.text=""
        gameFinished= false
        currentPlayer = if(currentPlayer == 1) 2 else 1
        tvPlayer1.setTextColor(if(currentPlayer == 1) Color.MAGENTA else Color.LTGRAY)
        tvPlayer2.setTextColor(if(currentPlayer == 2) Color.MAGENTA else Color.LTGRAY)
    }
}