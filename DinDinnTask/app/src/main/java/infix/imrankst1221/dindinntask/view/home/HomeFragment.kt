package infix.imrankst1221.dindinntask.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.Indicators.PagerIndicator
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import com.daimajia.slider.library.Tricks.ViewPagerEx
import com.google.android.material.appbar.AppBarLayout
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.core.BaseFragment
import infix.imrankst1221.dindinntask.view.home.food_menu.FoodMenuFragment
import infix.imrankst1221.dindinntask.view.home.food_menu.FoodMenuViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val foodMenuViewModel: FoodMenuViewModel by activityViewModel()

    private lateinit var sliderMaps: MutableMap<String, Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initImageSlider()
        intiViewPager()
        initListener()
    }

    private fun initImageSlider() {
        sliderMaps = LinkedHashMap<String, Int>()
        sliderMaps["1"] = R.drawable.img_demo_slider1
        sliderMaps["2"] = R.drawable.img_demo_slider2
        sliderMaps["3"] = R.drawable.img_demo_slider3

        for (name in sliderMaps.keys) {
            val textSliderView = DefaultSliderView(context)
            textSliderView
                .image(sliderMaps[name]!!)
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener { slider ->

                }

            textSliderView.bundle(Bundle())
            textSliderView.bundle.putString("extra", name)
            viewSliderLayout.addSlider(textSliderView)
        }
        viewSliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack)
        viewSliderLayout.setCustomIndicator(customIndicator as PagerIndicator)
        viewSliderLayout.setCustomAnimation(DescriptionAnimation())
        viewSliderLayout.setDuration(2000)
        viewSliderLayout.addOnPageChangeListener(object : ViewPagerEx.OnPageChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                try {

                } catch (ex: Exception) {
                }

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun intiViewPager(){
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FoodMenuFragment(1), getString(R.string.food_tab_item1))
        adapter.addFragment(FoodMenuFragment(2), getString(R.string.food_tab_item2))
        adapter.addFragment(FoodMenuFragment(3), getString(R.string.food_tab_item3))
        viewPagerFoodMenu.adapter = adapter
        tabFoodMenu.setupWithViewPager(viewPagerFoodMenu)
    }

    private fun initListener(){
        viewAppBar.addOnOffsetChangedListener(
                AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                if (verticalOffset == 0) {
                    txtCart.visibility = View.GONE
                    fabCart.hide()
                } else {
                    if(txtCart.text != "0" && txtCart.text != ""){
                        txtCart.visibility = View.VISIBLE
                    }
                    fabCart.show()
                }
            })

        // need to update with handler
        fabCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_checkoutFragment)
        }
    }

    override fun invalidate() =
        withState(foodMenuViewModel) { state ->
            when (state.rootList){
                is Loading -> {

                }
                is Success -> {
                    if(state.rootList.invoke().isNotEmpty()) {
                        val cartCount = state.rootList.invoke()[0].items.filter { it -> it.count > 0 }.size
                        if(cartCount == 0){
                            txtCart.visibility = View.GONE
                        }else {
                            txtCart.text = "$cartCount"
                            txtCart.visibility = View.VISIBLE
                        }
                    }
                }
                is Fail -> {
                    Toast.makeText(requireContext(), "Failed to load all Food!", Toast.LENGTH_SHORT).show()
                }
            }
        }

}

