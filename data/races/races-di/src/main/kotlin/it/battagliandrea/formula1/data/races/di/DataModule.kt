package it.battagliandrea.formula1.data.races.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.battagliandrea.formula1.data.races.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.races.impl.repository.ResultsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindRepository(repository: ResultsRepository): IResultsRepository
}
