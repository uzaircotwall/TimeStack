package za.edu.varcitycollege.st10091894.timetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //comment

        val loginPage = findViewById<Button>(R.id.register)
        loginPage.setOnClickListener {
            val loginPage = Intent(this, LoginActivity::class.java)
            startActivity(loginPage)

            val returnToLogin = findViewById<TextView>(R.id.goToLogin)
            returnToLogin.setOnClickListener {
                val returnToLogin = Intent(this, LoginActivity::class.java)
                startActivity(returnToLogin)
            }
        }
    }
}