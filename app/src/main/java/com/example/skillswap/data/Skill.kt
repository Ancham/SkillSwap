package com.example.skillswap.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills")
data class Skill(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val skillId: Int? = null,
    val name: String,
    val description: String,
    val swapOffer: String
)