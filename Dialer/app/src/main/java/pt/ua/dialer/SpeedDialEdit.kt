package pt.ua.dialer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.SharedPreferences


class SpeedDialEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speed_dial_edit)

        setSupportActionBar(findViewById(R.id.toolbar))

        val cancelButton = findViewById<Button>(R.id.btn_cancel)
        val clearButton = findViewById<Button>(R.id.btn_clear)
        val nameInput = findViewById<EditText>(R.id.name_input)
        val phoneInput = findViewById<EditText>(R.id.phone_input)

        val btnID: Int = intent.getIntExtra("btn", 0)
        Log.e("from btnID", btnID.toString())
        readFromSharedPreferences(btnID.toString(), nameInput, phoneInput)

        cancelButton.setOnClickListener {
            finish()
        }

        clearButton.setOnClickListener {
            val settings: SharedPreferences = this.getSharedPreferences("key", MODE_PRIVATE)
            settings.edit().remove(btnID.toString()).apply()

            val myToast = Toast.makeText(this, "Clear", Toast.LENGTH_SHORT)
            myToast.show()

            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save_contact -> {
            val nameInput = findViewById<EditText>(R.id.name_input)
            val phoneInput = findViewById<EditText>(R.id.phone_input)
            val btnID: Int = intent.getIntExtra("btn", 0)

            val name = nameInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()

            if (name.isBlank()) {
                val myToast = Toast.makeText(this, "Empty name", Toast.LENGTH_SHORT)
                myToast.show()
            } else if (phone.length < 3) {
                val myToast = Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT)
                myToast.show()
            } else {
                val c = Contact(name, phone)
                writeToSharedPreferences(btnID.toString(), c.toString())
                val myToast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT)
                myToast.show()

//                finish()
                val changeActivity = Intent(this, MainActivity::class.java)
                startActivity(changeActivity)
            }

            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }


    private fun writeToSharedPreferences(btn: String, contact: String) {
        val sharedPref = this.getSharedPreferences("key", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(btn, contact)
            apply()
        }
    }

    private fun readFromSharedPreferences(btnID: String, name: EditText, phone: EditText) {
        val sharedPref = this.getSharedPreferences("key", Context.MODE_PRIVATE) ?: return
        val contact = sharedPref.getString(btnID, "None")

        if (contact != "None") {
            val parts = contact.toString().split(",")
            val saved_name = parts[0]
            val saved_phone = parts[1]

            name.setText(saved_name)
            phone.setText(saved_phone)
        } else {
            name.setText("")
            phone.setText("")
        }
    }
}