package com.adrian.bucayan.spoonaculatormyrecipes.presentation.ui.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.bucayan.spoonaculatormyrecipes.common.Resource
import com.adrian.bucayan.spoonaculatormyrecipes.presentation.ui.MainActivity
import com.adrian.bucayan.spoonaculatormyrecipes.R
import com.adrian.bucayan.spoonaculatormyrecipes.common.Constants
import com.adrian.bucayan.spoonaculatormyrecipes.databinding.FragmentRecipeListBinding
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeViewModel by viewModels()
    private var recipeListAdapter : RecipeListAdapter? = null
    private var recipeList : List<Recipe>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.displayToolbar(true)
        (activity as MainActivity?)?.homeAsUpEnable(false)
        (activity as MainActivity?)?.toolBarTitle(getString(R.string.app_name))
        initRecyclerview()
        subscribeObservers()

        viewModel.onGetRecipeListEvent(GetRecipeListEvent.GetRecipeList)
    }

    private fun initRecyclerview() {
        binding.recipeListRecycler.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

            val itemDecoration: RecipeListAdapter.TopSpacingDecoration =
                RecipeListAdapter.TopSpacingDecoration(5)
            addItemDecoration(itemDecoration)

            recipeListAdapter = RecipeListAdapter()
            recipeListAdapter!!.toSelectRecipe = this@RecipeListFragment::toSelectRecipe
            adapter = recipeListAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.dataStateGetRecipeList.observe(viewLifecycleOwner) { dataStateGetRecipeList ->
            when (dataStateGetRecipeList) {

                is Resource.Success<List<Recipe>> -> {
                    Timber.e("dataStateGetRecipeList SUCCESS")
                    displayLoading(false)
                    binding.recipeListRecycler.visibility = View.VISIBLE
                    binding.recipeListEmptyMsg.visibility = View.GONE

                    recipeList = dataStateGetRecipeList.data
                    if (!recipeList.isNullOrEmpty()) {
                        recipeListAdapter!!.submitList(recipeList!!)
                    } else {
                        binding.recipeListEmptyMsg.visibility = View.VISIBLE
                    }

                }

                is Resource.Error -> {
                    Timber.e("dataStateGetRecipeList ERROR %s", dataStateGetRecipeList.message)
                    requireContext().toast(dataStateGetRecipeList.message.toString(), true)
                    displayLoading(false)
                }

                is Resource.Loading -> {
                    Timber.e("dataStateGetRecipeList LOADING")
                    displayLoading(true)
                }
            }
        }

    }

    private fun Context.toast(message: CharSequence, isLengthLong: Boolean = true) =
        Toast.makeText(
            this, message, if (isLengthLong) {
                Toast.LENGTH_LONG
            } else {
                Toast.LENGTH_SHORT
            }
        ).show()

    private fun displayLoading(isDisplayed: Boolean) {
        if (isDisplayed) {
            binding.linearProgressBarLoadMore.visibility = View.VISIBLE
        } else {
            binding.linearProgressBarLoadMore.visibility = View.GONE
        }
    }

    private fun toSelectRecipe(recipe: Recipe, position: Int?) {
        Timber.e("user = %s position = %s", recipe.title, position)
        val bundle = Bundle()
        bundle.putParcelable(Constants.SELECTED_RECIPE, recipe)
        findNavController().navigate(R.id.action_recipeListFragment_to_recipeFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}