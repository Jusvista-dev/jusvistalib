package mk.advtl.justorilib.utils

import android.app.Activity
import android.content.Intent
import mk.advtl.justorilib.vista.JusVistaActivity

class JusvistaBuilder {

    fun openJustories(context: Activity, requestCode: Int, checkToken: String, email: String) {


        start(context, requestCode, checkToken, email)
    }


    private fun start(context: Activity, requestCode: Int, checkToken: String, email: String) {

        val intent =
            Intent(context, JusVistaActivity::class.java).putExtra("checkToken", checkToken)
                .putExtra("email", email)
        context.startActivityForResult(intent, requestCode)
    }

    companion object {
        @JvmStatic
        val instance: JusvistaBuilder
            get() = JusvistaBuilder()
    }

}