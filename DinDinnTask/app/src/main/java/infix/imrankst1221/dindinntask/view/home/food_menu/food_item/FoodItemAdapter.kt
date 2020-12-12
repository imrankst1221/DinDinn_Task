package infix.imrankst1221.dindinntask.view.home.food_menu.food_item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.databinding.LayoutFilterBinding
import infix.imrankst1221.dindinntask.databinding.LayoutFoodItemBinding
import infix.imrankst1221.dindinntask.model.Filter
import infix.imrankst1221.dindinntask.model.Item
import java.util.*


class FoodItemAdapter:
        RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    private var mFoodItems: List<Item>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FoodItemViewHolder {
        val mFilterListItemBinding = DataBindingUtil.inflate<LayoutFoodItemBinding>(
                LayoutInflater.from(viewGroup.context),
                R.layout.layout_food_item, viewGroup, false
        )

        return FoodItemViewHolder(mFilterListItemBinding)
    }

    override fun onBindViewHolder(mFoodItemsViewHolder: FoodItemViewHolder, i: Int) {
        val item = mFoodItems!![i]
        mFoodItemsViewHolder.layoutFilterBinding.foodItem = item
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
