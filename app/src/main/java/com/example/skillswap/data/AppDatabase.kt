package com.example.skillswap.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Skill::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun skillDao(): SkillDao

    companion object {
        fun get(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()
        }
    }
}