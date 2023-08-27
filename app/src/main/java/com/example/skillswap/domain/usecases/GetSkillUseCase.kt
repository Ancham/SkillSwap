package com.example.skillswap.domain.usecases

import com.example.skillswap.data.Skill
import com.example.skillswap.data.SkillsRepository
import javax.inject.Inject

class GetSkillUseCase @Inject constructor(private val skillsRepository: SkillsRepository) {
    fun action(skillId: String): Skill {
        return skillsRepository.getSkill(skillId)
    }
}