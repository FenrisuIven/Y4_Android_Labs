package com.example.lb1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lb1.viewmodels.HomeScreenVM
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeScreenVM: HomeScreenVM = viewModel()
) {
    val firstBtnLabel = "remember"
    val secondBtnLabel = "viewModel"
    val thirdBtnLabel = "saveable"

    val firstButtonLabels = arrayOf("first", "second", "third", "last")
    val secondButtonLabels = arrayOf("1st", "2nd", "3rd", "4th", "5th", "Nth")
    val thirdButtonLabels = arrayOf("1", "2", "3", "4", "5", "N")

    var firstButtonTextIdx by remember { mutableStateOf(0) }
    var thirdButtonTextIdx by rememberSaveable { mutableStateOf(0) }
    val homeUiState by homeScreenVM.uiState.observeAsState()


    fun onClickFirstButton () {
        if (firstButtonTextIdx + 1 > firstButtonLabels.size - 1) {
            firstButtonTextIdx = 0;
        } else {
            firstButtonTextIdx += 1
        }
    }

    fun onClickThirdButton () {
        if (thirdButtonTextIdx + 1 > thirdButtonLabels.size - 1) {
            thirdButtonTextIdx = 0;
        } else {
            thirdButtonTextIdx += 1
        }
    }

    Column (
        modifier = modifier.padding(0.dp, 16.dp)
    ) {
        Text(
            text = "Home Screen",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.padding(16.dp, 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onClickFirstButton() }
                ) {
                    Text(text = firstBtnLabel)
                }
                Text(
                    text = firstButtonLabels[firstButtonTextIdx],
                    modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { homeScreenVM.changeState(0, secondButtonLabels.size) }
                ) {
                    Text(text = secondBtnLabel)
                }
                Text(
                    text = secondButtonLabels[homeUiState?.labelIdxValue!!],
                    modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onClickThirdButton() }
                ) {
                    Text(text = thirdBtnLabel)
                }
                Text(
                    text = thirdButtonLabels[thirdButtonTextIdx],
                    modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}