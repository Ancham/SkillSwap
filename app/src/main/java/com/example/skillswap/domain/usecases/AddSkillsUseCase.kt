package com.example.skillswap.domain.usecases

import com.example.skillswap.data.Skill
import com.example.skillswap.data.SkillsRepository
import javax.inject.Inject

class AddSkillsUseCase @Inject constructor(private val skillsRepository: SkillsRepository) {
    suspend fun action(skill: Skill): Unit {

        skillsRepository.insertSkill(skill)
//        return listOf(
//            Skill(skillId = "1", name = "yś", description = "yś", swapOffer = "yś"),
//            Skill(skillId = "2", name = "yś2", description = "yś2", swapOffer = "yś2")
//        )
    }

}