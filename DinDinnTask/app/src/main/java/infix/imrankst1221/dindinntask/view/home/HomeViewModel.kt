package infix.imrankst1221.dindinntask.view.home

import android.content.ClipData.Item
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.MvRxState
import infix.imrankst1221.dindinntask.CartUpdateInterface
import infix.imrankst1221.dindinntask.core.BaseViewModel


data class FormState(
    val cartCount: Int = 0,
) : MvRxState

class HomeViewModel(initialState: FormState) :
    BaseViewModel<FormState>(initialState) {

    init {
        logStateChanges()
    }

    private val selected = MutableLiveData<Boolean>()
    fun select(item: Boolean) {
        selected.value = item
    }

    fun getSelected(): LiveData<Boolean>? {
        return selected
    }
}
