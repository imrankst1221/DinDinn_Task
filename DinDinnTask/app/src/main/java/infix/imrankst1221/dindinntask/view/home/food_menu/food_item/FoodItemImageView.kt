package infix.imrankst1221.dindinntask.view.home.food_menu.food_item

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import infix.imrankst1221.dindinntask.R

class FoodItemImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    fun setUrl(url: String?) {
        if (url == null) {
            Picasso.with(context).cancelRequest(this)
            setImageDrawable(null)
            return
        }

        Picasso.with(context)
            .load(url)
            .placeholder(R.drawable.img_demo_slider1)
            .into(this)
    }
}
