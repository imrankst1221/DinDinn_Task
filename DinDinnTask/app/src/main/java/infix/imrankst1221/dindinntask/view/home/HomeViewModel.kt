package infix.imrankst1221.dindinntask.view.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Loading
import infix.imrankst1221.dindinntask.AppInstance

class HomeViewModel(
    context: Context,
    initialState: HomeState,
    homeRepository: HomeRepository
) : BaseMvRxViewModel<HomeState>(initialState, debugMode = true){

    val errorMessage = MutableLiveData<String>()

    init {
        setState {
            copy(foodMenuList = Loading())
        }
        homeRepository.getFoodMenuList(context)
            .execute {
                copy(foodMenuList = it) }
    }

    companion object : MvRxViewModelFactory<HomeViewModel, HomeState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: HomeState): HomeViewModel? {
            val foodMenuRepository = viewModelContext.app<AppInstance>().homeRepository
            return HomeViewModel(viewModelContext.activity, state, foodMenuRepository)
        }
    }

}
