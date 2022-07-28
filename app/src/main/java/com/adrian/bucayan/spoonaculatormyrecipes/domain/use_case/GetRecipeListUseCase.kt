package com.adrian.bucayan.spoonaculatormyrecipes.domain.use_case

import com.adrian.bucayan.spoonaculatormyrecipes.common.Constants
import com.adrian.bucayan.spoonaculatormyrecipes.common.Resource
import com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto.toRecipe
import com.adrian.bucayan.spoonaculatormyrecipes.domain.model.Recipe
import com.adrian.bucayan.spoonaculatormyrecipes.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipeListUseCase @Inject constructor(private val repository: MyRepository) {

    operator fun invoke(number: Int, apiKey: String): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading<List<Recipe>>())
            val getUserList = repository.getRecipeList(number, apiKey).recipes?.map{ it.toRecipe() }
            emit(Resource.Success<List<Recipe>>(getUserList!!))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Recipe>>(e.message?: Constants.ERROR_FROM_SERVER))
        } catch(e: IOException) {
            emit(Resource.Error<List<Recipe>>(e.message?: Constants.EXCEPTION_ERROR))
        }
    }


}