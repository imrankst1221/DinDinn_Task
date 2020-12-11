package infix.imrankst1221.dindinntask.view.home.food_menu

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.airbnb.mvrx.BaseMvRxViewModel
import infix.imrankst1221.dindinntask.AppInstance
import infix.imrankst1221.dindinntask.view.home.HomeRepository

class FoodMenuModel(
    context: Context,
    initialState: FoodMenuState,
    foodMenuRepository: HomeRepository
) : BaseMvRxViewModel<FoodMenuState>(initialState, debugMode = true){

    val errorMessage = MutableLiveData<String>()

    init {
        setState {
            copy(foodMenuList = Loading())
        }

        foodMenuRepository.getFoodMenuList(context)
            .execute {
                copy(foodMenuList = it) }
    }

    companion object : MvRxViewModelFactory<FoodMenuModel, FoodMenuState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: FoodMenuState): FoodMenuModel? {
            val homeRepository = viewModelContext.app<AppInstance>().homeRepository
            return FoodMenuModel(viewModelContext.activity, state, homeRepository)
        }
    }

}
