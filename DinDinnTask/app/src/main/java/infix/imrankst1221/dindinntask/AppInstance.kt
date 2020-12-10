package infix.imrankst1221.dindinntask

import android.app.Application
import infix.imrankst1221.dindinntask.view.home.HomeRepository


class AppInstance : Application() {
    val homeRepository by lazy {
        HomeRepository()
    }
}