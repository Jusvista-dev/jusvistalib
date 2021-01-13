package mk.advtl.sample

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import mk.advtl.justorilib.utils.JusvistaBuilder

class MainActivity : AppCompatActivity() {
    val REQUEST_CODE_STORY = 161
    var edtEmail: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtEmail = findViewById(R.id.edtEmail)
    }

    fun clickOnJustori(view: View) {
        try {
            var email = edtEmail!!.text.toString().trim()
            if (email.length > 8 && email.contains("@")) {
                JusvistaBuilder.instance.openJustories(this, REQUEST_CODE_STORY, "m1FCZGlF5V",email)
            } else {
                edtEmail!!.error = "Write your email please."
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun clickOnJustoriInvalid(view: View) {
        try {
            var email = edtEmail!!.text.toString().trim()
            if (email.length > 8 && email.contains("@")) {
                JusvistaBuilder.instance.openJustories(this, REQUEST_CODE_STORY, "testToken",email)
            } else {
                edtEmail!!.error = "Write your email please."
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}