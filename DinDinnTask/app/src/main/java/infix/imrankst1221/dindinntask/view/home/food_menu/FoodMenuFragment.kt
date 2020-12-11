package infix.imrankst1221.dindinntask.view.home.food_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.core.BaseFragment
import infix.imrankst1221.dindinntask.view.home.HomeViewModel

class FoodMenuFragment : BaseFragment() {
    private val viewModel: FoodMenuModel by activityViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.food_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun invalidate() =
            withState(viewModel) { currentState -> }
}