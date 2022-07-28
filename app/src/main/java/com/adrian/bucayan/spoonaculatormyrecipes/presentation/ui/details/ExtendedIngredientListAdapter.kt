package com.adrian.bucayan.spoonaculatormyrecipes.presentation.ui.details

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.adrian.bucayan.spoonaculatormyrecipes.R
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.ExtendedIngredient
import com.adrian.bucayan.spoonaculatormyrecipes.databinding.RowRecipeBinding
import com.adrian.bucayan.spoonaculatormyrecipes.databinding.RowRecipeIngredientsBinding
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.util.adapter.BaseRecyclerViewAdapter
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.util.adapter.BaseViewHolder
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.util.adapter.ViewHolderInitializer
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ExtendedIngredientListAdapter : BaseRecyclerViewAdapter<ExtendedIngredient, RowRecipeIngredientsBinding>() ,
    ViewHolderInitializer<ExtendedIngredient, RowRecipeIngredientsBinding> {

    var toSelectRecipe: ((ExtendedIngredient, Int) -> Unit)? = null

    init { viewBindingInitializer = this }

    class TopSpacingDecoration(private val padding: Int): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            if (parent.getChildAdapterPosition(view) > 0) {
                outRect.top = padding
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun generateViewHolder(parent: ViewGroup): BaseViewHolder<ExtendedIngredient, RowRecipeIngredientsBinding> {

        val itemBinding: RowRecipeIngredientsBinding = RowRecipeIngredientsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return UserListAdapterViewHolder(itemBinding, toSelectRecipe)

    }

}

@ExperimentalCoroutinesApi
class UserListAdapterViewHolder(
    viewBinding: RowRecipeIngredientsBinding,
    private val toSelectRecipe: ((ExtendedIngredient, Int) -> Unit)?
)  : BaseViewHolder<ExtendedIngredient, RowRecipeIngredientsBinding>(viewBinding) {

    private val title : TextView = viewBinding.rowRecipeIngName
    private val amount : TextView = viewBinding.rowRecipeIngAmountUnits
    private val image : ShapeableImageView = viewBinding.rowRecipeIngImg

    @SuppressLint("SetTextI18n", "ResourceType")
    override fun setViews(item: ExtendedIngredient) {
        super.setViews(item)

        title.text = item.originalName
        amount.text = item.amount.toString() + " " + item.unit.toString()

        image.load("https://spoonacular.com/cdn/ingredients_100x100/" + item.image.toString()) {
            crossfade(true)
            placeholder(R.drawable.rounded_corner_blue)
            scale(Scale.FIT)
            transformations(RoundedCornersTransformation(60f))
        }

    }



}

