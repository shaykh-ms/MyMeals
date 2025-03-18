package com.example.mymeals.di


import com.example.mymeals.data.remote.MealApi
import com.example.mymeals.data.repository.MealRepositoryImpl
import com.example.mymeals.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMealRepository(
        mealApi: MealApi
    ): MealRepository {
        return MealRepositoryImpl(mealApi)
    }
}