package uz.gita.jaxongir.gamexoonline.domain.usecase.impl

import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.domain.usecase.GetMyNameUseCase
import javax.inject.Inject

class GetMyNameUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetMyNameUseCase {
    override fun invoke(): String = repository.getName()
}