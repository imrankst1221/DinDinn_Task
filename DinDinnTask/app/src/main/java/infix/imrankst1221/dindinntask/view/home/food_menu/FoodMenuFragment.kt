package infix.imrankst1221.dindinntask.view.home.food_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.core.BaseFragment
import infix.imrankst1221.dindinntask.view.home.food_menu.filter.FoodFilterAdapter
import infix.imrankst1221.dindinntask.view.home.food_menu.food_item.FoodItemAdapter
import kotlinx.android.synthetic.main.food_menu_fragment.*

class FoodMenuFragment(position: Int) : BaseFragment() {
    private val viewViewModel: FoodMenuViewModel by parentFragmentViewModel()
    private lateinit var foodFilterAdapter: FoodFilterAdapter
    private lateinit var foodItemAdapter: FoodItemAdapter
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
        recyclerViewFilter.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)
        recyclerViewFilter.setHasFixedSize(true)
        recyclerViewFilter.adapter = foodFilterAdapter

        foodItemAdapter = FoodItemAdapter()
        recyclerViewFoodMenu.layoutManager = LinearLayoutManager(context)
        recyclerViewFoodMenu.setHasFixedSize(true)
        recyclerViewFoodMenu.adapter = foodItemAdapter
    }

    override fun invalidate() =
            withState(viewViewModel) { state ->
                when (state.foodMenuList){
                    is Loading -> {

                    }
                    is Success -> {
                        if(state.foodMenuList.invoke().isNotEmpty()) {
                            foodFilterAdapter.setFilterList(state.foodMenuList.invoke()[position].filters)
                            foodItemAdapter.setFoodItemList(state.foodMenuList.invoke()[position].items)
                        }
                    }
                    is Fail -> {
                        Toast.makeText(requireContext(), "Failed to load all Food!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
}