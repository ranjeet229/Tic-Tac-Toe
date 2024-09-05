package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var currentPlayer = "X"
    private var board = Array(3) { arrayOfNulls<String>(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var btn1 =findViewById<Button>(R.id.btn1)
        var btn2 =findViewById<Button>(R.id.btn2)
        var btn3 =findViewById<Button>(R.id.btn3)
        var btn4 =findViewById<Button>(R.id.btn4)
        var btn5 =findViewById<Button>(R.id.btn5)
        var btn6 =findViewById<Button>(R.id.btn6)
        var btn7 =findViewById<Button>(R.id.btn7)
        var btn8 =findViewById<Button>(R.id.btn8)
        var btn9 =findViewById<Button>(R.id.btn9)

        val buttons = listOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)


        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                makeMove(button, index / 3, index % 3)
            }

    }
}

    private fun makeMove(button: Button, row: Int, col: Int) {
        if (board[row][col] != null) {
            Toast.makeText(this, "Cell already filled", Toast.LENGTH_SHORT).show()
            return
        }

        // Update board with current player
        board[row][col] = currentPlayer
        button.text = currentPlayer

        if (currentPlayer == "X") {

            button.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            button.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        // Check  a winner
        if (checkWinner()) {
            Toast.makeText(this, "Player $currentPlayer wins!", Toast.LENGTH_LONG).show()
            resetBoard()
        } else if (isBoardFull()) {
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_LONG).show()
            resetBoard()
        } else {
            // Switch player turns
            currentPlayer = if (currentPlayer == "X") "O" else "X"
        }
    }

    // Check if the current player has won
    private fun checkWinner(): Boolean {

        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true

        return false
    }

    // Check if the board is full
    private fun isBoardFull(): Boolean {
        for (row in board) {
            if (row.contains(null)) {
                return false
            }
        }
        return true
    }

    // Reset the board for a new game
    private fun resetBoard() {
        board = Array(3) { arrayOfNulls<String>(3) }
        val buttons = listOf(
            findViewById<Button>(R.id.btn1), findViewById<Button>(R.id.btn2), findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4), findViewById<Button>(R.id.btn5), findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7), findViewById<Button>(R.id.btn8), findViewById<Button>(R.id.btn9)
        )

        // clear all
        buttons.forEach { it.text = "" }

        // Reset player
        currentPlayer = "X"
    }
}