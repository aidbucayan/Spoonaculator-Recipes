package com.adrian.bucayan.spoonaculatormyrecipes.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrian.bucayan.spoonaculatormyrecipes.common.Resource
import com.adrian.bucayan.spoonaculatormyrecipes.common.Constants
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Recipe
import com.adrian.bucayan.spoonaculatormyrecipes.domain.use_case.GetRecipeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipeListUseCase: GetRecipeListUseCase
): ViewModel() {

    private val _dataStateGetRecipeList: MutableLiveData<Resource<List<Recipe>>> = MutableLiveData()

    val dataStateGetRecipeList: LiveData<Resource<List<Recipe>>> = _dataStateGetRecipeList

    fun onGetRecipeListEvent(event: GetRecipeListEvent) {
        viewModelScope.launch {
            when(event) {
                is GetRecipeListEvent.GetRecipeList -> {
                    getRecipeListUseCase(20, Constants.APIKEY)
                        .onEach { dataStateGetRecipeList ->
                            _dataStateGetRecipeList.value = dataStateGetRecipeList
                        }
                        .launchIn(viewModelScope)
                }
            }
        }

    }

}

sealed class GetRecipeListEvent{
    object GetRecipeList: GetRecipeListEvent()
}