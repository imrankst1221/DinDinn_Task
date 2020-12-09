package infix.imrankst1221.dindinntask.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.daimajia.slider.library.Tricks.ViewPagerEx
import infix.imrankst1221.dindinntask.FormViewModel
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.core.BaseFragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {
    // Instantiate view model with state.
    private val viewModel: FormViewModel by activityViewModel()
    private lateinit var file_maps: MutableMap<String, Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initImageSlider()
    }

    private fun initImageSlider() {
        file_maps = LinkedHashMap<String, Int>()
        //slide home image
        file_maps["1"] = R.drawable.img_demo_slider
        file_maps["2"] = R.drawable.img_demo_slider
        file_maps["3"] = R.drawable.img_demo_slider

        for (name in file_maps.keys) {
            val textSliderView = DefaultSliderView(context)
            // initialize a SliderLayout
            textSliderView
                //.description(name)
                .image(file_maps[name]!!)
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener { slider ->

                }

            //add your extra information
            textSliderView.bundle(Bundle())
            textSliderView.bundle.putString("extra", name)
            viewSliderLayout.addSlider(textSliderView)
        }
        viewSliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack)
        viewSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        viewSliderLayout.setCustomAnimation(DescriptionAnimation())
        viewSliderLayout.setDuration(8000)
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

    // Update UI.
    override fun invalidate() =
        withState(viewModel) { currentState ->
        }

}
