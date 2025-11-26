package com.example.cosmeticsapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val btnCreate = findViewById<Button>(R.id.btnCreate)

        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                val sel = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@CreateAccountActivity, "Gender: $sel", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((s?.length ?: 0) < 8) {
                    etPassword.error = getString(R.string.password_short)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        btnCreate.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || pass.length < 6) {
                Toast.makeText(this, getString(R.string.fill_all), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))) {
                Toast.makeText(this, "Email invalide", Toast.LENGTH_SHORT).show()
              return@setOnClickListener
            }

            if (!pass.matches(Regex("^[A-Za-z0-9]{8,}$"))){
                Toast.makeText(this,"Password Incorrect ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ProductsActivity::class.java)
            intent.putExtra("userName", name)
            startActivity(intent)
        }
    }
}
