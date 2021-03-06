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
import kotlinx.android.synthetic.main.fragment_food_menu.*

class FoodMenuFragment(private val categoryId: Int) : BaseFragment() {
    private val viewViewModel: FoodMenuViewModel by parentFragmentViewModel()
    private lateinit var foodFilterAdapter: FoodFilterAdapter
    private lateinit var foodItemAdapter: FoodItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListView()
    }

    private fun initListView(){
        foodFilterAdapter = FoodFilterAdapter()
        recyclerViewFilter.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)
        recyclerViewFilter.setHasFixedSize(true)
        recyclerViewFilter.adapter = foodFilterAdapter

        foodItemAdapter = FoodItemAdapter(object : FoodItemAdapter.FoodAddListListener{
            override fun addToCart(itemId: Int) {
                viewViewModel.addFoodItemToCart(itemId)
            }

        })
        recyclerViewFoodMenu.layoutManager = LinearLayoutManager(context)
        recyclerViewFoodMenu.setHasFixedSize(true)
        recyclerViewFoodMenu.adapter = foodItemAdapter
    }

    override fun invalidate() =
            withState(viewViewModel) { state ->
                when (state.rootList){
                    is Loading -> {

                    }
                    is Success -> {
                        if(state.rootList.invoke().isNotEmpty()) {
                            foodFilterAdapter.setFilterList(state.rootList.invoke()[0].filters)
                            foodItemAdapter.setFoodItemList(state.rootList.invoke()[0].items.filter { it -> it.categoryId == categoryId })
                        }
                    }
                    is Fail -> {
                        Toast.makeText(requireContext(), "Failed to load all Food!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
}