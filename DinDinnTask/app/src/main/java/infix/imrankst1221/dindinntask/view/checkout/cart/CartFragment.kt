package infix.imrankst1221.dindinntask.view.checkout.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.core.BaseFragment
import infix.imrankst1221.dindinntask.model.Item
import infix.imrankst1221.dindinntask.view.home.food_menu.FoodMenuViewModel
import infix.imrankst1221.dindinntask.view.home.food_menu.filter.FoodFilterAdapter
import infix.imrankst1221.dindinntask.view.home.food_menu.food_item.FoodItemAdapter
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : BaseFragment() {
    private val viewViewModel: FoodMenuViewModel by parentFragmentViewModel()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListView()
    }

    private fun initListView(){
        cartAdapter = CartAdapter(object : CartAdapter.CartListListener{
            override fun onRemoveFromCart(itemId: Int) {
                viewViewModel.removeFoodItemToCart(itemId)
            }

        })
        recyclerViewCart.layoutManager = LinearLayoutManager(context)
        recyclerViewCart.setHasFixedSize(true)
        recyclerViewCart.adapter = cartAdapter
    }

    override fun invalidate() =
            withState(viewViewModel) { state ->
                when (state.rootList){
                    is Loading -> {
                    }
                    is Success -> {
                        if(state.rootList.invoke().isNotEmpty()) {
                            cartAdapter.setFoodItemList(state.rootList.invoke()[0].items.filter { it -> it.count > 0 } as ArrayList<Item>)
                        }
                    }
                    is Fail -> {
                        Toast.makeText(requireContext(), "Failed to load all Food!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
}