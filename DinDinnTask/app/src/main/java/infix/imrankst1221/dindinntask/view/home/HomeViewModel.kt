package infix.imrankst1221.dindinntask.view.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Loading
import infix.imrankst1221.dindinntask.AppInstance

class HomeViewModel(
    context: Context,
    initialState: HomeState) : BaseMvRxViewModel<HomeState>(initialState, debugMode = true){

    companion object : MvRxViewModelFactory<HomeViewModel, HomeState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: HomeState): HomeViewModel? {

            return HomeViewModel(viewModelContext.activity, state)
        }
    }

}
