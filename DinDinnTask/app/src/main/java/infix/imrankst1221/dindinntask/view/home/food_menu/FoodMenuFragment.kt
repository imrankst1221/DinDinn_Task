package infix.imrankst1221.dindinntask.view.home.food_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.core.BaseFragment
import infix.imrankst1221.dindinntask.view.home.food_menu.filter.FoodFilterAdapter
import kotlinx.android.synthetic.main.food_menu_fragment.*

class FoodMenuFragment(position: Int) : BaseFragment() {
    private val viewModel: FoodMenuModel by parentFragmentViewModel()
    private var foodFilterAdapter: FoodFilterAdapter? = null
    private val position: Int = position

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.food_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initFilterList()
    }

    private fun initFilterList(){
        foodFilterAdapter = FoodFilterAdapter()
        recyclerViewFilter!!.layoutManager = LinearLayoutManager(context)
        recyclerViewFilter!!.setHasFixedSize(true)
        recyclerViewFilter!!.adapter = foodFilterAdapter

    }

    override fun invalidate() =
            withState(viewModel) { state ->
                /*if(state.foodMenuList is Success){
                    foodFilterAdapter!!.setFilterList(state.foodMenuList.invoke()[position].filters)
                }*/

                when (state.foodMenuList){
                    is Loading -> {

                    }
                    is Success -> {
                        if(state.foodMenuList.invoke().size > 0 ) {
                            foodFilterAdapter!!.setFilterList(state.foodMenuList.invoke()[0].filters)
                        }
                    }
                    is Fail -> {
                        Toast.makeText(requireContext(), "Failed to load all Food!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
}