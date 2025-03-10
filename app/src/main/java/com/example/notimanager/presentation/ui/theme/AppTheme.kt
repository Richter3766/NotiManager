package com.example.notimanager.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable

// TODO: SharedPreference 에서 적절한 TypoGraphy를 가져와서 적용하기
@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
    typography = AppTypography,
    shapes = Shapes(),
    content = content
    )
}