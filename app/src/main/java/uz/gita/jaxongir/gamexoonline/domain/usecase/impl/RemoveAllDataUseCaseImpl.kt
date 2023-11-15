package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.RemoveAllDataUseCase
import javax.inject.Inject

class RemoveAllDataUseCaseImpl @Inject constructor(
    private val repository: Repository
) : RemoveAllDataUseCase {
    override fun invoke() = repository.removeAllData()
}