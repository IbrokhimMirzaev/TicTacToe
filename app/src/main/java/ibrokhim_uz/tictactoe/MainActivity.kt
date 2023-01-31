package ibrokhim_uz.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var matrix = Array(3) { IntArray(3) { -1 } }
    var active = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        active_player.text = "Player X"

        img0.setOnClickListener(this)
        img1.setOnClickListener(this)
        img2.setOnClickListener(this)
        img3.setOnClickListener(this)
        img4.setOnClickListener(this)
        img5.setOnClickListener(this)
        img6.setOnClickListener(this)
        img7.setOnClickListener(this)
        img8.setOnClickListener(this)
        restart.setOnClickListener {
            restart()
        }

    }

    override fun onClick(p0: View?) {
        val img = findViewById<ImageButton>(p0!!.id)
        val t = img.tag.toString().toInt()
        val col: Int = t / 3
        val row: Int = t % 3
        if (matrix[col][row] == -1) {
            if (active) {
                img.setImageResource(R.drawable.x_sign)
                active = false
                matrix[col][row] = 1
                isWinner(1)
                active_player.text = "Player O"
            } else {
                img.setImageResource(R.drawable.o_sign)
                active = true
                matrix[col][row] = 0
                isWinner(0)
                active_player.text = "Player X"
            }
        }


    }

    var count = 0
    private fun isWinner(a: Int) {
        // by horizontal side e.g: left to right
        horizontalCheck(a)
        count = 0

        // by vertical side e.g: top to bottom
        verticalCheck(a)
        count = 0

        // x 0 0
        // 0 x 0
        // 0 0 x
        fromLeftTopToRightBottomCheck(a)
        count = 0

        // 0 0 x
        // 0 x 0
        // x 0 0
        fromRightTopToBottomCheck(a)
        count = 0
    }

    private fun horizontalCheck(a: Int) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (matrix[i][j] == a) {
                    count++
                }
            }
            showWinnerName(a)
            count = 0
        }
    }

    private fun verticalCheck(a: Int) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (matrix[j][i] == a) {
                    count++
                }
            }
            showWinnerName(a)
            count = 0
        }
    }

    private fun fromLeftTopToRightBottomCheck(a: Int) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (i == j) {
                    if (matrix[j][i] == a) {
                        count++
                    }
                }
            }
        }
        showWinnerName(a)
    }

    private fun fromRightTopToBottomCheck(a: Int) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (i + j == 2) {
                    if (matrix[j][i] == a) {
                        count++
                    }
                }
            }
        }
        showWinnerName(a)
    }

    private fun finishGame() {
        img0.isEnabled = false
        img1.isEnabled = false
        img2.isEnabled = false
        img3.isEnabled = false
        img4.isEnabled = false
        img5.isEnabled = false
        img6.isEnabled = false
        img7.isEnabled = false
        img8.isEnabled = false
        restart.visibility = View.VISIBLE
    }

    private fun restart() {
        matrix = Array(3) { IntArray(3) { -1 } }
        active = true
        active_player.text = "Player X"

        restart.visibility = View.INVISIBLE

        winner.text = ""

        img0.isEnabled = true
        img1.isEnabled = true
        img2.isEnabled = true
        img3.isEnabled = true
        img4.isEnabled = true
        img5.isEnabled = true
        img6.isEnabled = true
        img7.isEnabled = true
        img8.isEnabled = true

        img0.setImageDrawable(null)
        img1.setImageDrawable(null)
        img2.setImageDrawable(null)
        img3.setImageDrawable(null)
        img4.setImageDrawable(null)
        img5.setImageDrawable(null)
        img6.setImageDrawable(null)
        img7.setImageDrawable(null)
        img8.setImageDrawable(null)
    }

    private fun showWinnerName(a: Int) {
        var winnerName = ""
        winnerName = if (a == 0) "Player O" else "Player X"
        if (count == 3) {
            winner.text = "Winner is $winnerName"
            finishGame()
            return
        }
    }
}