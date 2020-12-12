package infix.imrankst1221.dindinntask.view.home.food_menu.food_item

import android.os.Build
import android.os.Looper
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.databinding.LayoutFoodItemBinding
import infix.imrankst1221.dindinntask.model.Item


class FoodItemAdapter(private val foodAddListListener: FoodAddListListener):
        RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    interface FoodAddListListener{
        fun addToCart(itemId: Int)
    }

    private var mFoodItems: List<Item>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FoodItemViewHolder {
        val mFilterListItemBinding = DataBindingUtil.inflate<LayoutFoodItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.layout_food_item, viewGroup, false
        )

        return FoodItemViewHolder(mFilterListItemBinding)
    }

    override fun onBindViewHolder(mFoodItemsViewHolder: FoodItemViewHolder, position: Int) {
        val item = mFoodItems!![position]
        val binding = mFoodItemsViewHolder.layoutFilterBinding
        val context = binding.root.context

        binding.foodItem = item
        binding.btnAddCart.text = ""+item.price +" usd"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.txtDetails.setText(
                Html.fromHtml(item.details, Html.FROM_HTML_MODE_LEGACY),
                TextView.BufferType.SPANNABLE
            )
        } else {
            binding.txtDetails.setText(Html.fromHtml(item.details), TextView.BufferType.SPANNABLE)
        }

        binding.btnAddCart.setOnClickListener {
            foodAddListListener.addToCart(item.id)

            binding.btnAddCart.text = context.getString(R.string.food_menu_add_1)
            binding.btnAddCart.background = context.getDrawable(R.drawable.bg_food_list_button_selected)

            android.os.Handler(Looper.getMainLooper()).postDelayed({
                binding.btnAddCart.text = "" + item.price + " usd"
                binding.btnAddCart.background = context.getDrawable(R.drawable.bg_food_list_button)
            }, 500)
        }
    }

    override fun getItemCount(): Int {
        return if (mFoodItems != null) {
            mFoodItems!!.size
        } else {
            0
        }
    }

    fun setFoodItemList(mFoodItems: List<Item>) {
        this.mFoodItems = mFoodItems
        notifyDataSetChanged()
    }

    inner class FoodItemViewHolder(var layoutFilterBinding: LayoutFoodItemBinding) :
            RecyclerView.ViewHolder(layoutFilterBinding.root)

}