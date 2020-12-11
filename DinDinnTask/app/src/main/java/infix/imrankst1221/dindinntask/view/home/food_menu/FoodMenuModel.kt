package infix.imrankst1221.dindinntask.view.home.food_menu

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.airbnb.mvrx.BaseMvRxViewModel
import infix.imrankst1221.dindinntask.AppInstance

class FoodMenuModel(
    context: Context,
    foodMenuState: FoodMenuState,
    foodMenuRepository: FoodMenuRepository
) : BaseMvRxViewModel<FoodMenuState>(foodMenuState, debugMode = true){

    val errorMessage = MutableLiveData<String>()

    init {
        foodMenuRepository.getFoodMenuList(context)
            .execute {
                copy(foodMenuList = it)
            }
    }

    companion object : MvRxViewModelFactory<FoodMenuModel, FoodMenuState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: FoodMenuState): FoodMenuModel? {
            val foodMenuRepository = viewModelContext.app<AppInstance>().foodMenuRepository
            return FoodMenuModel(viewModelContext.activity, state, foodMenuRepository)
        }
    }

}
