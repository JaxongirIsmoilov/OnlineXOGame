package uz.gita.jaxongir.gamexoonline.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.repository.impl.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindRepository(impl: RepositoryImpl): Repository

}