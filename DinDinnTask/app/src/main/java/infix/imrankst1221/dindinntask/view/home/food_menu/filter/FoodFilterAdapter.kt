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

    private var mFilters: List<Filter>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FilterViewHolder {
        val mFilterListItemBinding = DataBindingUtil.inflate<LayoutFilterBinding>(
                LayoutInflater.from(viewGroup.context),
                R.layout.layout_filter, viewGroup, false
        )

        return FilterViewHolder(mFilterListItemBinding)
    }

    override fun onBindViewHolder(mFilterViewHolder: FilterViewHolder, i: Int) {
        val item = mFilters!![i]
        mFilterViewHolder.layoutFilterBinding.filter = item
    }

    override fun getItemCount(): Int {
        return if (mFilters != null) {
            mFilters!!.size
        } else {
            0
        }
    }

    fun setFilterList(mFilters: List<Filter>) {
        this.mFilters = mFilters
        notifyDataSetChanged()
    }

    inner class FilterViewHolder(var layoutFilterBinding: LayoutFilterBinding) :
            RecyclerView.ViewHolder(layoutFilterBinding.root)
}
