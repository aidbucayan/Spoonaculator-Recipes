package com.adrian.bucayan.spoonaculatormyrecipes.data.repository

import com.adrian.bucayan.spoonaculatormyrecipes.data.remote.MyApi
import com.adrian.bucayan.spoonaculatormyrecipes.data.remote.dto.RecipeResponseDto
import com.adrian.bucayan.spoonaculatormyrecipes.domain.repository.MyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MyRepository {

    override suspend fun getRecipeList(number: Int, apiKey: String): RecipeResponseDto {
       /* val userListDto : List<UserDto>
        withContext(ioDispatcher) {
            userListDto = api.getUsers()
        }*/
        return api.getUsers(number, apiKey)
    }

}