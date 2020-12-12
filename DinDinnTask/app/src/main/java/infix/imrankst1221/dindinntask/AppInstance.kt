package infix.imrankst1221.dindinntask

import android.app.Application
import infix.imrankst1221.dindinntask.view.home.food_menu.FoodMenuRepository


class AppInstance : Application() {
    val foodMenuRepository by lazy {
        FoodMenuRepository()
    }
}