package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.RemoveValueUseCase
import javax.inject.Inject

class RemoveValueUseCaseImpl @Inject constructor(
    private val repository: Repository
) : RemoveValueUseCase {
    override fun invoke() = repository.removeValue()
}