package com.adrian.bucayan.spoonaculatormyrecipes.presentation.ui.list

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
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Recipe
import com.adrian.bucayan.spoonaculatormyrecipes.databinding.RowRecipeBinding
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.util.adapter.BaseRecyclerViewAdapter
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.util.adapter.BaseViewHolder
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.util.adapter.ViewHolderInitializer
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.ExperimentalCoroutinesApi

class RecipeListAdapter : BaseRecyclerViewAdapter<Recipe, RowRecipeBinding>() ,
    ViewHolderInitializer<Recipe, RowRecipeBinding> {

    var toSelectRecipe: ((Recipe, Int) -> Unit)? = null

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
    override fun generateViewHolder(parent: ViewGroup): BaseViewHolder<Recipe, RowRecipeBinding> {

        val itemBinding: RowRecipeBinding = RowRecipeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return UserListAdapterViewHolder(itemBinding, toSelectRecipe)

    }

}

@ExperimentalCoroutinesApi
class UserListAdapterViewHolder(
    viewBinding: RowRecipeBinding,
    private val toSelectRecipe: ((Recipe, Int) -> Unit)?
)  : BaseViewHolder<Recipe, RowRecipeBinding>(viewBinding) {

    private val holder : RelativeLayout = viewBinding.rowRecipeHolder
    private val title : TextView = viewBinding.rowRecipeTitle
    private val price : TextView = viewBinding.rowRecipePrice
    private val dishTypes : TextView = viewBinding.rowRecipeDishType
    private val noLikes : TextView = viewBinding.rowRecipeNoLikes
    private val image : ShapeableImageView = viewBinding.rowRecipeImage

    @SuppressLint("SetTextI18n", "ResourceType")
    override fun setViews(item: Recipe) {
        super.setViews(item)

        title.text = item.title
        price.text = item.pricePerServing.toString()

        val dishTypeStringBuilder = StringBuilder()
        for (i in 0 until item.dishTypes!!.size) {
            dishTypeStringBuilder.append(item.dishTypes!![i] + " . ")
        }

        dishTypes.text = dishTypeStringBuilder
        noLikes.text = item.aggregateLikes.toString()

        image.load(item.image) {
            crossfade(true)
            placeholder(R.drawable.rounded_corner_blue)
            scale(Scale.FILL)
            transformations(RoundedCornersTransformation(20f))
        }

        holder.setOnClickListener{
            toSelectRecipe?.invoke(item, adapterPosition)
        }


    }



}

