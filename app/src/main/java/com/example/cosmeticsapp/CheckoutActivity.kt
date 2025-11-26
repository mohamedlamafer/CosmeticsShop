package com.example.cosmeticsapp

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    private lateinit var etAddress: EditText
    private lateinit var etPhone: EditText
    private lateinit var etCIN: EditText
    private lateinit var etCardNumber: EditText
    private lateinit var spinnerCardType: Spinner
    private lateinit var btnConfirm: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        etAddress = findViewById(R.id.etAddress)
        etPhone = findViewById(R.id.etPhone)
        etCIN = findViewById(R.id.etCIN)
        etCardNumber = findViewById(R.id.etCardNumber)
        spinnerCardType = findViewById(R.id.spinnerCardType)
        btnConfirm = findViewById(R.id.btnConfirm)
        btnCancel = findViewById(R.id.btnCancel)

        val cardTypes = listOf("Visa", "MasterCard", "Maestro", "Amex", "Autre")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cardTypes)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCardType.adapter = spinnerAdapter

        btnCancel.setOnClickListener { finish() }

        btnConfirm.setOnClickListener {
            if (validateInputs()) {
                val address = etAddress.text.toString().trim()
                val phone = etPhone.text.toString().trim()
                val cin = etCIN.text.toString().trim()
                val card = etCardNumber.text.toString().trim()
                val cardType = spinnerCardType.selectedItem.toString()


                AlertDialog.Builder(this)
                    .setTitle("Commande confirmée")
                    .setMessage("Adresse: $address\nTéléphone: $phone\nCIN: $cin\nCarte: ${maskCardNumber(card)}\nType: $cardType\n\nMerci pour votre achat !")
                    .setPositiveButton("OK") { _, _ ->
                        Cart.clear()
                        finish()
                    }
                    .show()
            }
        }
    }

    private fun validateInputs(): Boolean {
        if (TextUtils.isEmpty(etAddress.text.toString().trim())) {
            etAddress.error = "Adresse requise"
            etAddress.requestFocus()
            return false
        }

        val phone = etPhone.text.toString().trim()
        if (phone.isEmpty() || !phone.matches(Regex("^[0-9]{10,}$"))) {
            etPhone.error = "Numéro téléphone invalide"
            etPhone.requestFocus()
            return false
        }

        val cin = etCIN.text.toString().trim()
        if (!cin.matches(Regex("^[A-Za-z0-9]{6,12}$"))) {
            Toast.makeText(this, "CIN invalide", Toast.LENGTH_SHORT).show()
            return false
        }


        val card = etCardNumber.text.toString().replace("\\s".toRegex(), "")
        if (!card.matches(Regex("^[0-9]{12,19}\$"))) {
            etCardNumber.error = "Numéro de carte invalide"
            etCardNumber.requestFocus()
            return false
        }



        return true
    }

    private fun maskCardNumber(number: String): String {
        val cleaned = number.replace("\\s".toRegex(), "")
        return if (cleaned.length <= 4) cleaned else "**** **** **** " + cleaned.takeLast(4)
    }
}
