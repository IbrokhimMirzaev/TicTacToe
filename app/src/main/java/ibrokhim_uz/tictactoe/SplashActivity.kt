package ibrokhim_uz.tictactoe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}