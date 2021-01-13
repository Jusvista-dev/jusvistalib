package mk.advtl.justorilib.vista

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import mk.advtl.justorilib.R
import java.text.SimpleDateFormat
import java.util.*


open class BaseActivity : AppCompatActivity() {

    var alertDialog: Dialog? = null

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onBackPressed() {

        try {
            Log.e("clicked", "Clicked >>  Manual")

            var openDialog = Dialog(this, R.style.DialogTheme)
            openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            openDialog.setContentView(R.layout.popup_exit)
            openDialog.window
                ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            openDialog.window?.setGravity(Gravity.BOTTOM)
            openDialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            val btnCancel: Button =
                openDialog.findViewById(R.id.btnCancel)
            val btnLogout: Button =
                openDialog.findViewById(R.id.btnLogout)

            btnCancel.setOnClickListener {
                openDialog.dismiss()
            }
            btnLogout.setOnClickListener {
                openDialog.dismiss()
                super.onBackPressed()
            }

            openDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        /* if (doubleBackToExitPressedOnce) {
             super.onBackPressed()
             return
         }

         this.doubleBackToExitPressedOnce = true
         Toast.makeText(this, "Please click BACK again to close the app", Toast.LENGTH_SHORT).show()

         Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)*/
    }

    open fun formatDate(
        date: String?,
        initDateFormat: String?,
        endDateFormat: String?
    ): String? {
        return try {
            val initDate: Date = SimpleDateFormat(initDateFormat).parse(date)
            val formatter = SimpleDateFormat(endDateFormat)
            formatter.format(initDate)
        } catch (e: Exception) {
            e.printStackTrace()
            "Invalid Date Format"
        }
    }

    fun loadProgressDialogue(context: Activity) {
        try {
            alertDialog = Dialog(context)
            alertDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            alertDialog!!.setCancelable(false)
            alertDialog!!.setContentView(R.layout.loader_dialog)
            alertDialog!!.window!!.setBackgroundDrawableResource(R.color.colorDarkTransparent)
            alertDialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
//            alertDialog!!.

            // dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openLoader() {
        try {
            alertDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun closeLoader() {
        try {
            alertDialog!!.dismiss()
        } catch (e: Exception) {
        }
    }

}


