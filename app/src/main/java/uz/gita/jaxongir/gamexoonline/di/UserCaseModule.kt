package uz.gita.jaxongir.gamexoonline.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.jaxongir.gamexoonline.domain.usecase.AddUserUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.AttachUsersUserCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.BattleFlowUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.GameDataStateFlowUserCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.GetMyNameUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.RemoveAllDataUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.RemoveValueUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.UpdateMapUserCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.UserDataStateFlowUseCase
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.AddUserUseCaseImpl
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.AttachUserUserCaseImpl
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.BattleFlowUseCaseImpl
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.GameDataStateFlowUserCaseImpl
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.GetMyNameUseCaseImpl
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.RemoveAllDataUseCaseImpl
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.RemoveValueUseCaseImpl
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.UpdateMapUserCaseImpl
import uz.gita.jaxongir.gamexoonline.domain.usecase.impl.UserDataStateFlowUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserCaseModule {

    @[Binds Singleton]
    fun bindAddUseUseCase(impl: AddUserUseCaseImpl): AddUserUseCase

    @[Binds Singleton]
    fun bindUserDataStateFlowUseCase(impl: UserDataStateFlowUseCaseImpl): UserDataStateFlowUseCase

    @[Binds Singleton]
    fun bindUpdateMapUserCase(impl: UpdateMapUserCaseImpl): UpdateMapUserCase

    @[Binds Singleton]
    fun bindGameDataStateFlowUserCase(impl: GameDataStateFlowUserCaseImpl): GameDataStateFlowUserCase

    @[Binds Singleton]
    fun bindAttachUserUseCase(impl: AttachUserUserCaseImpl): AttachUsersUserCase

    @[Binds Singleton]
    fun bindBattleFlowUserCase(impl: BattleFlowUseCaseImpl): BattleFlowUseCase

    @[Binds Singleton]
    fun bindRemoveValue(impl: RemoveValueUseCaseImpl): RemoveValueUseCase

    @[Binds Singleton]
    fun bindGetMyNameUseCase(impl: GetMyNameUseCaseImpl): GetMyNameUseCase

    @[Binds Singleton]
    fun bindRemoveAllDataUseCase(impl: RemoveAllDataUseCaseImpl): RemoveAllDataUseCase

}