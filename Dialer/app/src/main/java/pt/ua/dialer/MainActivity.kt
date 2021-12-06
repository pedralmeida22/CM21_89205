package pt.ua.dialer

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number = findViewById<TextView>(R.id.editTextPhone)
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btnAst = findViewById<Button>(R.id.btn_ast)
        val btnCard = findViewById<Button>(R.id.btn_cardinal)
        val btnCall = findViewById<Button>(R.id.btn_call)
        val btnDel = findViewById<Button>(R.id.btn_del)
        val btnSpeedDial1 = findViewById<Button>(R.id.speed_dial1)
        val btnSpeedDial2 = findViewById<Button>(R.id.speed_dial2)
        val btnSpeedDial3 = findViewById<Button>(R.id.speed_dial3)

        readContactNameFromSharedPreferences(btnSpeedDial1)
        readContactNameFromSharedPreferences(btnSpeedDial2)
        readContactNameFromSharedPreferences(btnSpeedDial3)

        btn0.setOnClickListener {
           updateNumber(btn0.text.toString())
        }

        btn1.setOnClickListener {
            updateNumber(btn1.text.toString())
        }

        btn2.setOnClickListener {
            updateNumber(btn2.text.toString())
        }

        btn3.setOnClickListener {
            updateNumber(btn3.text.toString())
        }

        btn4.setOnClickListener {
            updateNumber(btn4.text.toString())
        }

        btn5.setOnClickListener {
            updateNumber(btn5.text.toString())
        }

        btn6.setOnClickListener {
            updateNumber(btn6.text.toString())
        }

        btn7.setOnClickListener {
            updateNumber(btn7.text.toString())
        }

        btn8.setOnClickListener {
            updateNumber(btn8.text.toString())
        }

        btn9.setOnClickListener {
            updateNumber(btn9.text.toString())
        }

        btnAst.setOnClickListener {
            updateNumber(btnAst.text.toString())
        }

        btnCard.setOnClickListener {
            updateNumber(btnCard.text.toString())
        }

        btnCall.setOnClickListener {
            call()
        }

        btnDel.setOnClickListener {
            var numberText = number.text.toString()

            if (numberText.isNotEmpty()) {
                number.text = numberText.subSequence(0, numberText.length - 1)
            }
        }

        btnSpeedDial1.setOnClickListener {
            if(btnSpeedDial1.text == "Empty"){
                val myToast = Toast.makeText(this, "No saved number!", Toast.LENGTH_LONG)
                myToast.show()
            }
            else{
                setPhoneNumberFromSharedPreferences(btnSpeedDial1, number as EditText)
            }
        }

        btnSpeedDial2.setOnClickListener {
            if(btnSpeedDial2.text == "Empty"){
                val myToast = Toast.makeText(this, "No saved number!", Toast.LENGTH_LONG)
                myToast.show()
            }
            else{
                setPhoneNumberFromSharedPreferences(btnSpeedDial2, number as EditText)
            }
        }

        btnSpeedDial3.setOnClickListener {
            if(btnSpeedDial3.text == "Empty"){
                val myToast = Toast.makeText(this, "No saved number!", Toast.LENGTH_LONG)
                myToast.show()
            }
            else{
                setPhoneNumberFromSharedPreferences(btnSpeedDial3, number as EditText)
            }
        }

        btnSpeedDial1.setOnLongClickListener {
            changeActivity(btnSpeedDial1.id)
            true
        }

        btnSpeedDial2.setOnLongClickListener {
            changeActivity(btnSpeedDial2.id)
            true
        }

        btnSpeedDial3.setOnLongClickListener {
            changeActivity(btnSpeedDial3.id)
            true
        }

    }

    private fun changeActivity(btnID: Int){
        val changeActivity = Intent(this, SpeedDialEdit::class.java)
        changeActivity.putExtra("btn", btnID)
        startActivity(changeActivity)
    }

    private fun readContactNameFromSharedPreferences(btn: Button) {
        val sharedPref = this.getSharedPreferences("key", Context.MODE_PRIVATE) ?: return
        val contact = sharedPref.getString(btn.id.toString(), "None")

        if (contact == "None"){
            btn.text = "Empty"
        }
        else{
            val parts = contact.toString().split(",")
            val saved_name = parts[0]

            btn.text = saved_name
        }
    }

    private fun setPhoneNumberFromSharedPreferences(btn: Button, phone: EditText){
        val sharedPref = this.getSharedPreferences("key", Context.MODE_PRIVATE) ?: return
        val contact = sharedPref.getString(btn.id.toString(), "None")

        if (contact == "None"){
            phone.setText("")
        }
        else{
            val parts = contact.toString().split(",")
            val saved_number = parts[1]

            phone.setText(saved_number)
        }
    }

    private fun call(){
        if (getNumber().length < 3) {
            val myToast = Toast.makeText(this, "Insert valid number!", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + getNumber())
            startActivity(dialIntent)
        }
    }

    private fun updateNumber(string: String){
        val number = findViewById<TextView>(R.id.editTextPhone)
        var numberText = number.text.toString()

        number.text = numberText + string
    }

    private fun getNumber() : String {
        val number = findViewById<TextView>(R.id.editTextPhone)
        return number.text.toString()
    }

}
