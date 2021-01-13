package mk.advtl.justorilib.retrofit

import android.util.Log
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Moumi on 20/09/2020.
 */
object RetrofitError {

    fun codeToErrorMessage(code: Int): String {
        if (code in 400..499)
            return "serverIssues"
        else if (code in 400..499)
            return "serverIssues"
        else {
            Log.e("Code ", "Error Code >> $code")
            return "unResolveErrorr"
        }
    }

    fun showErrorMessage(error: Throwable): String {
        return when (error) {
            is SocketTimeoutException -> {
                "No Internet Connection"
            }
            is UnknownHostException -> {
                "serverIssues"
            }
            is HttpException -> {
                val code = (error as HttpException).code()
                codeToErrorMessage(code)
            }
            else -> {
                Log.e("Code ", "Error has no clue >> ")
                "unResolveErrorr"
            }
        }
    }
}
