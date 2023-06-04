package za.edu.varcitycollege.st10091894.timetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // for testing purposes
        val nextPage = findViewById<Button>(R.id.login)

        nextPage.setOnClickListener() {
            val nextPage = Intent(this, MainActivity::class.java)
            startActivity(nextPage)
            finish()
        }

        val registerPage = findViewById<TextView>(R.id.goToRegister)
        registerPage.setOnClickListener(){
            val registerPage = Intent(this, RegisterActivity::class.java)
            startActivity(registerPage)
        }
    }
}