package com.example.skillswap.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {

    @Query("SELECT * FROM skills ORDER BY name")
    fun getSkills(): Flow<List<Skill>>

    @Query("SELECT * FROM skills WHERE id=:skillId")
    fun getSkill(skillId: String): Skill

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkill(skill: Skill)
}