package com.example.skillswap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.skillswap.ui.theme.SkillSwapTheme
import androidx.core.view.WindowCompat
import com.example.skillswap.presentation.skillSwap.SkillSwapApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SkillSwapTheme {
                SkillSwapApp()
            }
        }
    }

}


