package za.edu.varcitycollege.st10091894.timetracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etvTaskName: EditText
    private lateinit var edtPassword: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etvTaskName = findViewById(R.id.etvTaskName)
        edtPassword = findViewById(R.id.edtPassword)
        loginButton = findViewById(R.id.login)

        loginButton.setOnClickListener {
            val username = etvTaskName.text.toString()
            val password = edtPassword.text.toString()

            if (isValidCredentials(username, password)) {
                // Valid credentials, perform login logic here
            } else {
                // Invalid credentials, show an error message
                etvTaskName.error = "Invalid username or password"
                edtPassword.error = "Invalid username or password"
            }
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // Implement your own validation logic here
        return username.isNotEmpty() && password.isNotEmpty()
    }
}
