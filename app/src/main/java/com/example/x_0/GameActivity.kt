package com.example.x_0

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
    }
    fun play(btn: View){
        val mybtn: Button= btn as Button
        if(!gameFinished && mybtn.text.toString().isEmpty()){
            if(currentPlayer == 1){
                mybtn.text = "X"
                validateWinner()
                currentPlayer = 2
            }
            else{
                mybtn.text = "O"
                validateWinner()
                currentPlayer = 1
            }

        }
    }
    fun validateWinner(){
        var bt1Val = bt1.text.toString().trim()
        var bt2Val = bt2.text.toString().trim()
        var bt3Val = bt3.text.toString().trim()
        var bt4Val = bt4.text.toString().trim()
        var bt5Val = bt5.text.toString().trim()
        var bt6Val = bt6.text.toString().trim()
        var bt7Val = bt7.text.toString().trim()
        var bt8Val = bt8.text.toString().trim()
        var bt9Val = bt9.text.toString().trim()
        val bool = if (!bt1Val.isEmpty() && bt1Val.equals(bt2Val) && bt1Val.equals(bt3Val))
                ||
                (!bt1Val.isEmpty() && bt1Val.equals(bt2Val) && bt1Val.equals(bt3Val)){
                    if (currentPlayer == 1) {
                        scorePlayer1++
                        tvscoreplayer1.text = "$scorePlayer1"
                        Toast.makeText(
                            applicationContext,
                            "${tvPlayer1.text} Ha Ganado",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    } else {
                        scorePlayer2++
                        tvscoreplayer2.text = "$scorePlayer2"
                        Toast.makeText(
                            applicationContext,
                            "${tvPlayer2.text} Ha Ganado",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    gameFinished = true
                }

    }

    private fun validateCards(): Boolean {
        TODO("Not yet implemented")
    }
}