package uz.gita.jaxongir.gamexoonline.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.jaxongir.gamexoonline.presenter.screens.game.GameDirection
import uz.gita.jaxongir.gamexoonline.presenter.screens.game.GameDirectionImpl
import uz.gita.jaxongir.gamexoonline.presenter.screens.start.StartDirection
import uz.gita.jaxongir.gamexoonline.presenter.screens.start.StartDirectionImpl
import uz.gita.jaxongir.gamexoonline.presenter.screens.users.UsersDirection
import uz.gita.jaxongir.gamexoonline.presenter.screens.users.UsersDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun provideStartDirection(impl: StartDirectionImpl): StartDirection

    @Binds
    fun bindGameDirection(impl: GameDirectionImpl): GameDirection

    @Binds
    fun bindUserDirection(impl: UsersDirectionImpl): UsersDirection

}