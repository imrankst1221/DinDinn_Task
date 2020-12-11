package infix.imrankst1221.dindinntask.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
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
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel by activityViewModel()
    private lateinit var sliderMaps: MutableMap<String, Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
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
        adapter.addFragment(FoodMenuFragment(), "Pizza")
        adapter.addFragment(FoodMenuFragment(), "Sushi")
        adapter.addFragment(FoodMenuFragment(), "Drinks")
        viewPagerFoodMenu.adapter = adapter
        tabFoodMenu.setupWithViewPager(viewPagerFoodMenu)
    }

    private fun initListener(){
        viewAppBar.addOnOffsetChangedListener(
                AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                if (verticalOffset == 0) {
                    viewFab.hide()
                } else {
                    viewFab.show()
                }
            })
    }

    // Update UI.
    override fun invalidate() =
        withState(viewModel) { currentState -> }

}

