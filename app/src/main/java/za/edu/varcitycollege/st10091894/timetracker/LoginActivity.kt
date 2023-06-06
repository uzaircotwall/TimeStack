package za.edu.varcitycollege.st10091894.timetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import za.edu.varcitycollege.st10091894.timetracker.Lists.UsersList
import za.edu.varcitycollege.st10091894.timetracker.models.UserModel
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {

    private lateinit var etvTaskName: EditText
    private lateinit var edtPassword: EditText

    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etvTaskName = findViewById(R.id.edtPersonEmail)
        edtPassword = findViewById(R.id.edtPassword)
        loginButton = findViewById(R.id.login)

        val registerPage = findViewById<TextView>(R.id.goToRegister)
        registerPage.setOnClickListener(){
            val registerPage = Intent(this, RegisterActivity::class.java)
            startActivity(registerPage)
            finish()
        }

        loginButton.setOnClickListener {
            val username = etvTaskName.text.toString()
            val password = edtPassword.text.toString()

           // if (isValidCredentials(etvTaskName, edtPassword)) {
                // Valid credentials, perform login logic here
                val goToMainActivity = Intent(this, MainActivity::class.java)
                startActivity(goToMainActivity)
                finish()

           // }
        }
    }

    private fun isValidCredentials(username: EditText, password: EditText): Boolean {
        // Implement your own validation logic here
        var isValid = false
        if (username.text.toString().isEmpty()){
            username.error = "Invalid username or password"
        }
        if (password.text.toString().isEmpty()){
            password.error = "Invalid username or password"
        }
        UsersList.userList.forEach {
            if (it.email.equals(username.text.toString(), ignoreCase = true) && it.password == hashPassword(password.text.toString())){
                if (isValid == false) isValid = true
            } else{
                username.error = "Invalid username or password"
                password.error = "Invalid username or password"
            }
        }

        return isValid
    }

    fun hashPassword(password: String): String{
        val md = MessageDigest.getInstance("SHA-256")
        val hashBytes = md.digest(password.toByteArray(StandardCharsets.UTF_8))

        //convert the byte array to haxadecimal string representation
        val hexString = java.lang.StringBuilder()
        for (byte in hashBytes){
            val hex = String.format("%02x", byte)
            hexString.append(hex)
        }

        return hexString.toString()
    }
}
