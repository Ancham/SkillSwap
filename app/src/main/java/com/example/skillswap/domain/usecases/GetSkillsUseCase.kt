package com.example.skillswap.domain.usecases

import com.example.skillswap.data.Skill
import com.example.skillswap.data.SkillsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSkillsUseCase @Inject constructor(private val skillsRepository: SkillsRepository) {
    fun action(): List<Skill> {

        return skillsRepository.getSkills()
//        return listOf(
//            Skill(skillId = "1", name = "yś", description = "yś", swapOffer = "yś"),
//            Skill(skillId = "2", name = "yś2", description = "yś2", swapOffer = "yś2")
//        )
    }
}