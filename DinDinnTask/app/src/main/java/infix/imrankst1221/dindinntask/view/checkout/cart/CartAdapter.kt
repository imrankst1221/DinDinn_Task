package infix.imrankst1221.dindinntask.view.checkout.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.databinding.LayoutCartItemBinding
import infix.imrankst1221.dindinntask.model.Filter
import infix.imrankst1221.dindinntask.model.Item
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter(private val cartListListener: CartListListener): RecyclerView.Adapter<CartAdapter.FilterViewHolder>() {

    interface CartListListener{
        fun onRemoveFromCart(itemId: Int)
    }

    private var mFoodItems: ArrayList<Item> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FilterViewHolder {
        val mFilterListItemBinding = DataBindingUtil.inflate<LayoutCartItemBinding>(
                LayoutInflater.from(viewGroup.context),
                R.layout.layout_cart_item, viewGroup, false
        )
        return FilterViewHolder(mFilterListItemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(mFilterViewHolder: FilterViewHolder, position: Int) {
        val item = mFoodItems[position]
        val binding = mFilterViewHolder.layoutCartItemBinding
        binding.foodItem = item
        binding.btnRemoveFromCart.setOnClickListener {
            cartListListener.onRemoveFromCart(item.id)
            mFoodItems.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return mFoodItems.size
    }

    fun setFoodItemList(mFoodItems: ArrayList<Item>) {
        this.mFoodItems = mFoodItems
        notifyDataSetChanged()
    }

    inner class FilterViewHolder(var layoutCartItemBinding: LayoutCartItemBinding) :
            RecyclerView.ViewHolder(layoutCartItemBinding.root)
}
