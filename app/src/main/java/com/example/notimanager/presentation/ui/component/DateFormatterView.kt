package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notimanager.R
import com.example.notimanager.presentation.stateholder.viewmodel.DateFormatterViewModel

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
                    Text(relativeTime)
                    Text(relativeExample)
                    Text(exampleExplanation)
                }
            }
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
                    Text(absoluteTime)
                    Text(absoluteExample)
                    Text(exampleExplanation)
                }
            }
            Button(
                onClick = { viewModel.setDateFormat(selectedOption) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(apply)
            }
        }

    }
}