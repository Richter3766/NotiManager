package com.notimanager.notimanager.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.notimanager.notimanager.R
import com.notimanager.notimanager.presentation.stateholder.viewmodel.DateFormatterViewModel

@Composable
fun DateFormatterView(
    innerPadding: PaddingValues,
    viewModel: DateFormatterViewModel = hiltViewModel()
    ){
    // 언어 설정에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val relativeTime = context.getString(R.string.format_relative_time)
    val absoluteTime = context.getString(R.string.format_absolute_time)
    val relativeExample = context.getString(R.string.format_relative_example)
    val absoluteExample = context.getString(R.string.format_absolute_example)
    val exampleExplanation = context.getString(R.string.format_example_explanation)
    val apply = context.getString(R.string.format_apply)

    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        var selectedOption by remember { mutableStateOf(viewModel.getDateFormat()) }

        Column {
            // 상대 시간 설정
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable(onClick = { selectedOption = viewModel.relativeTime })
            )  {
                RadioButton(
                    selected = selectedOption == viewModel.relativeTime,
                    onClick = { selectedOption = viewModel.relativeTime }
                )
                Column{
                    Text(
                        text = relativeTime,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = exampleExplanation,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = relativeExample,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }

            // 절대 시간 설정
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable(onClick = { selectedOption = viewModel.absoluteTime })
            )  {
                RadioButton(
                    selected = selectedOption == viewModel.absoluteTime,
                    onClick = { selectedOption = viewModel.absoluteTime }
                )
                Column{
                    Text(
                        text = absoluteTime,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = exampleExplanation,
                        style = MaterialTheme.typography.bodySmall
                        )
                    Text(
                        text = absoluteExample,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }
            Button(
                onClick = { viewModel.setDateFormat(selectedOption) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF673AB7) // 원하는 배경 색상으로 변경
                )
            ) {
                Text(apply)
            }
        }
    }
}