package com.example.skillswap.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.skillswap.presentation.base.BaseActivity
import com.example.skillswap.presentation.swapApp.SwapApp
import com.example.skillswap.ui.theme.SkillSwapTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillSwapTheme {
                SwapApp()
            }
        }
    }

}
