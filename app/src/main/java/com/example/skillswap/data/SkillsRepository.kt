package com.example.skillswap.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillsRepository @Inject constructor(private val skillDao: SkillDao) {
    fun getSkills() = skillDao.getSkills()
    fun getSkill(skillId: String) = skillDao.getSkill(skillId)
    suspend fun insertSkill(skill: Skill) = skillDao.insertSkill(skill)
}