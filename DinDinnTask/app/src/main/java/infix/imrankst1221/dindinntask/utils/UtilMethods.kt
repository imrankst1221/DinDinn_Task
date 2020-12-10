package infix.imrankst1221.dindinntask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

object UtilMethods {
    /**
     * @param context
     * @return true or false mentioning the device is connected or not
     * @brief checking the internet connection on run time
     */
    private val TYPE_WIFI = 1
    private val TYPE_MOBILE = 2
    private fun getConnectivityStatus(context: Context): Int {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null) {
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI && networkInfo.state == NetworkInfo.State.CONNECTED) {

                return TYPE_WIFI

            } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE && networkInfo.state == NetworkInfo.State.CONNECTED) {
                return TYPE_MOBILE
            }
        }
        return 0
    }

    fun isConnectedToInternet(context: Context): Boolean {
        val networkStatus = getConnectivityStatus(context)
        return networkStatus == TYPE_WIFI || networkStatus == TYPE_MOBILE
    }

    /**
     * short toast
     * @context is application context
     * @message is the show message
     */
    fun showLongToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}