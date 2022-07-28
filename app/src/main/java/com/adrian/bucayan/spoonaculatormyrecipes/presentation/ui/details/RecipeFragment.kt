package com.adrian.bucayan.spoonaculatormyrecipes.presentation.ui.details

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.ui.MainActivity
import com.adrian.bucayan.spoonaculatormyrecipes.R
import com.adrian.bucayan.spoonaculatormyrecipes.common.Constants
import com.adrian.bucayan.spoonaculatormyrecipes.databinding.FragmentRecipeBinding
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.ExtendedIngredient
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Recipe
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.ui.list.RecipeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class RecipeFragment : Fragment(R.layout.fragment_recipe) {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recipe: Recipe
    private var extendedIngredientListAdapter : ExtendedIngredientListAdapter? = null
    private var extendedIngredientList : List<ExtendedIngredient>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.displayToolbar(true)
        (activity as MainActivity?)?.homeAsUpEnable(true)
        recipe = arguments?.getParcelable(Constants.SELECTED_RECIPE)!!
        (activity as MainActivity?)?.toolBarTitle(getString(R.string.information))
        initRecyclerview()
        populateViewWithUserData(recipe)
    }

    private fun initRecyclerview() {
        binding.recipesIngRecycler.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

            val itemDecoration: RecipeListAdapter.TopSpacingDecoration =
                RecipeListAdapter.TopSpacingDecoration(5)
            addItemDecoration(itemDecoration)

            extendedIngredientListAdapter = ExtendedIngredientListAdapter()
            adapter = extendedIngredientListAdapter
        }
    }

    private fun populateViewWithUserData(thisRecipe: Recipe) {
        binding.rowRecipeTitle.text = thisRecipe.title
        binding.rowRecipeImage.load(thisRecipe.image) {
            crossfade(true)
            placeholder(R.drawable.rounded_corner_blue)
            scale(Scale.FILL)
            transformations(RoundedCornersTransformation(20f))
        }
        binding.rowRecipeNoLikes.text = thisRecipe.aggregateLikes.toString() + " Likes"
        binding.rowRecipeNoServing.text = thisRecipe.servings.toString() + " Serving"
        binding.rowRecipeNoPrice.text = thisRecipe.pricePerServing.toString() + " Price"
        binding.rowRecipeNoTime.text = thisRecipe.readyInMinutes.toString() + " Minutes"
        binding.rowRecipeIngDescTxt.text = thisRecipe.summary?.let { stripHtml(it) }

        extendedIngredientList = thisRecipe.extendedIngredients
        if (!extendedIngredientList.isNullOrEmpty()) {
            extendedIngredientListAdapter!!.submitList(extendedIngredientList!!)
        }
        binding.rowRecipeIngCount.text = "Ingredients(" + extendedIngredientList!!.size + ")"
    }

    private fun stripHtml(html: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(html).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}