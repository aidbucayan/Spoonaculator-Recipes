package com.adrian.bucayan.spoonaculatormyrecipes.di

import android.content.Context
import android.util.Log
import androidx.viewbinding.BuildConfig
import com.adrian.bucayan.spoonaculatormyrecipes.data.remote.MyApi
import com.adrian.bucayan.spoonaculatormyrecipes.data.repository.MyRepositoryImpl
import com.adrian.bucayan.spoonaculatormyrecipes.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyRepository(api: MyApi): MyRepository {
        return MyRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun providesTimberTree(): Timber.Tree {
        class ReportingTree : Timber.Tree() {
            override fun log(
                priority: Int,
                tag: String?,
                message: String,
                throwable: Throwable?)
            {
                if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                    return
                }
            }
        }

        return when(BuildConfig.DEBUG) {
            true -> Timber.DebugTree()
            else -> ReportingTree()
        }
    }

}