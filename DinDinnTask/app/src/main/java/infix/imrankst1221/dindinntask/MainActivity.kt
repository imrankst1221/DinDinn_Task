package infix.imrankst1221.dindinntask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity
import com.airbnb.mvrx.MvRxState
import infix.imrankst1221.dindinntask.core.BaseViewModel

// State
data class FormState(
    val cartCount: Int = 0,
) : MvRxState

// ViewModel
class FormViewModel(initialState: FormState) :
    BaseViewModel<FormState>(initialState) {
    init {
        logStateChanges()
    }

}

class MainActivity : BaseMvRxActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}