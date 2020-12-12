package infix.imrankst1221.dindinntask.view.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.core.BaseFragment
import infix.imrankst1221.dindinntask.view.checkout.cart.CartFragment
import infix.imrankst1221.dindinntask.view.checkout.infromation.InformationFragment
import infix.imrankst1221.dindinntask.view.checkout.order.OrderFragment
import infix.imrankst1221.dindinntask.view.home.ViewPagerAdapter
import infix.imrankst1221.dindinntask.view.home.food_menu.FoodMenuFragment
import infix.imrankst1221.dindinntask.view.home.food_menu.FoodMenuViewModel
import kotlinx.android.synthetic.main.fragment_checkout.*

class CheckoutFragment : BaseFragment() {
    private val foodMenuViewModel: FoodMenuViewModel by parentFragmentViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        intiViewPager()
        initListener()
    }

    private fun intiViewPager(){
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(CartFragment(), getString(R.string.cart_tab_item1))
        adapter.addFragment(OrderFragment(),  getString(R.string.cart_tab_item2))
        adapter.addFragment(InformationFragment(),  getString(R.string.cart_tab_item3))
        viewPagerCheckout.adapter = adapter
        tabCheckout.setupWithViewPager(viewPagerCheckout)
    }

    private fun initListener(){
        // need to update with handler
        fabCard.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_paymentFragment)
        }
        toolbar.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    override fun invalidate() {
        // on update
    }
}
