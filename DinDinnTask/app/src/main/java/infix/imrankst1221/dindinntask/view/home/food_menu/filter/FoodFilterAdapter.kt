package infix.imrankst1221.dindinntask.view.home.food_menu.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.databinding.LayoutFilterBinding
import infix.imrankst1221.dindinntask.model.Filter
import java.util.*


class FoodFilterAdapter:
        RecyclerView.Adapter<FoodFilterAdapter.FilterViewHolder>() {

    private var mFilterModel: List<Filter>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FilterViewHolder {
        val mFilterListItemBinding = DataBindingUtil.inflate<LayoutFilterBinding>(
                LayoutInflater.from(viewGroup.context),
                R.layout.layout_filter, viewGroup, false
        )

        return FilterViewHolder(mFilterListItemBinding)
    }

    override fun onBindViewHolder(mFilterViewHolder: FilterViewHolder, i: Int) {
        val item = mFilterModel!![i]
        mFilterViewHolder.mFilterListItemBinding.filter = item
    }

    override fun getItemCount(): Int {
        return if (mFilterModel != null) {
            mFilterModel!!.size
        } else {
            0
        }
    }

    fun setFilterList(mFilterModel: List<Filter>) {
        this.mFilterModel = mFilterModel
        notifyDataSetChanged()
    }

    inner class FilterViewHolder(var mFilterListItemBinding: LayoutFilterBinding) :
            RecyclerView.ViewHolder(mFilterListItemBinding.root)
}
