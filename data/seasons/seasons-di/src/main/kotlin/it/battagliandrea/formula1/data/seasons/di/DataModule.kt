package it.battagliandrea.formula1.data.seasons.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.battagliandrea.formula1.data.seasons.api.ISeasonsRepository
import it.battagliandrea.formula1.data.seasons.impl.repository.SeasonsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindRepository(repository: SeasonsRepository): ISeasonsRepository
}
