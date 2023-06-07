package za.edu.varcitycollege.st10091894.timetracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import za.edu.varcitycollege.st10091894.timetracker.Lists.UsersList
import za.edu.varcitycollege.st10091894.timetracker.models.UserModel
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //comment

         val name = findViewById<EditText>(R.id.edtPersonName)
         val surname = findViewById<EditText>(R.id.edtPersonName)
         val phone = findViewById<EditText>(R.id.edtPhone)
         val email = findViewById<EditText>(R.id.edtPersonEmail)
         val password = findViewById<EditText>(R.id.edtPassword)
         val confirmPassword = findViewById<EditText>(R.id.edtConfirmPassword)

        val loginPage = findViewById<Button>(R.id.register)
        loginPage.setOnClickListener {
            val loginPage = Intent(this, LoginActivity::class.java)

            if (inputIsValid(name, surname, email, password, confirmPassword )){
                val user = UserModel(name.text.toString(), surname.text.toString(), phone.text.toString(),
                email.text.toString(), hashPassword(password.text.toString())
                )

                UsersList.userList.add(user)
                startActivity(loginPage)
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
            }

        }

        val returnToLogin = findViewById<TextView>(R.id.goToLogin)
        returnToLogin.setOnClickListener {
            val returnToLogin = Intent(this, LoginActivity::class.java)
            startActivity(returnToLogin)
        }
    }

    private fun inputIsValid(name: EditText, surname: EditText, email: EditText,
                             password: EditText, confirmPassword: EditText): Boolean {
        var inputValid = true

        if (name.text.toString().isEmpty()){
            inputValid = false
            name.error = "please enter your name"
        }
        if (surname.text.toString().isEmpty()){
            inputValid = false
            surname.error = "please enter your surname"
        }
        if (email.text.toString().isEmpty()){
            inputValid = false
            email.error = "please enter your Email"
        }
        if (password.text.toString().isEmpty()){
            inputValid = false
            password.error = "please enter a password"
        }
        if (confirmPassword.text.toString().isEmpty()){
            inputValid = false
            confirmPassword.error = "please confirm your password"
        }
        if (confirmPassword.text.toString() != password.text.toString()){
            inputValid = false
            confirmPassword.error = "passwords do not match"
        }

        return inputValid
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

