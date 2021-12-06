package pt.ua.dialer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SpeedDialEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speed_dial_edit)

        val cancelButton = findViewById<Button>(R.id.btn_cancel)
        val saveButton = findViewById<Button>(R.id.btn_save)
        val nameInput = findViewById<EditText>(R.id.name_input)
        val phoneInput = findViewById<EditText>(R.id.phone_input)

        val btnID: Int = intent.getIntExtra("btn", 0)
        Log.e("from btnID", btnID.toString())
        readFromSharedPreferences(btnID.toString(), nameInput, phoneInput)

        cancelButton.setOnClickListener {
            val changeActivity = Intent(this, MainActivity::class.java)
            startActivity(changeActivity)
        }

        saveButton.setOnClickListener {
            if (nameInput.text.toString().isBlank()){
                val myToast = Toast.makeText(this, "Empty name", Toast.LENGTH_SHORT)
                myToast.show()
            }

            else if(phoneInput.text.toString().length < 3){
                val myToast = Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT)
                myToast.show()
            }

            else{
                val name = nameInput.text.toString().trim()
                val phone = phoneInput.text.toString().trim()
                val c = Contact(name, phone)
                writeToSharedPreferences(btnID.toString(), c.toString())
                val myToast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT)
                myToast.show()

                val changeActivity = Intent(this, MainActivity::class.java)
                startActivity(changeActivity)
            }
        }
    }

    private fun writeToSharedPreferences(btn: String, contact: String){
        val sharedPref = this.getSharedPreferences("key", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(btn, contact)
            apply()
        }
    }

    private fun readFromSharedPreferences(btnID: String, name: EditText, phone: EditText){
        val sharedPref = this.getSharedPreferences("key", Context.MODE_PRIVATE) ?: return
        val contact = sharedPref.getString(btnID, "None")

        if (contact != "None"){
            val parts = contact.toString().split(",")
            val saved_name = parts[0]
            val saved_phone = parts[1]

            name.setText(saved_name)
            phone.setText(saved_phone)
        }
        else{
            name.setText("")
            phone.setText("")
        }
    }
}