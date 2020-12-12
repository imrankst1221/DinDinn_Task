package infix.imrankst1221.dindinntask.view.home.food_menu

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.airbnb.mvrx.BaseMvRxViewModel
import infix.imrankst1221.dindinntask.AppInstance

class FoodMenuViewModel(
    context: Context,
    foodMenuState: FoodMenuState,
    private val foodMenuRepository: FoodMenuRepository
) : BaseMvRxViewModel<FoodMenuState>(foodMenuState, debugMode = true){

    init {
        foodMenuRepository.getFoodMenuList(context)
            .execute {
                copy(foodMenuList = it)
            }
    }

    fun addFoodItemToCart(categoryId: Int, itemId: Int){
        withState { state: FoodMenuState ->
            if(state.foodMenuList is Success){
                foodMenuRepository
            }
        }
    }

    companion object : MvRxViewModelFactory<FoodMenuViewModel, FoodMenuState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: FoodMenuState): FoodMenuViewModel? {
            val foodMenuRepository = viewModelContext.app<AppInstance>().foodMenuRepository
            return FoodMenuViewModel(viewModelContext.activity, state, foodMenuRepository)
        }
    }

}
