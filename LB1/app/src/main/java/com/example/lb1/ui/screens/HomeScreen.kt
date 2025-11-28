package com.example.lb1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.lb1.model.LabeledButton

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeScreenVM = viewModel()
) {
    val firstButton = LabeledButton(
      label = "remember",
      descriptors = arrayOf("first", "second", "third", "last")
    )

    val secondButton = LabeledButton(
      label = "viewModel",
      descriptors = arrayOf("1st", "2nd", "3rd", "4th", "5th", "Nth")
    )

    val thirdButton = LabeledButton(
      label = "saveable",
      descriptors = arrayOf("1", "2", "3", "4", "5", "N")
    )

    var firstIdx by remember { mutableStateOf(0) }
    var thirdIdx by rememberSaveable { mutableStateOf(0) }
    val homeUiState by viewModel.uiState.observeAsState()


    fun onClickFirstButton () {
        firstIdx = firstButton.incDescriptorIdx(firstIdx)
    }

    fun onClickThirdButton () {
        thirdIdx = thirdButton.incDescriptorIdx(thirdIdx)
    }

    Column (
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(16.dp, 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onClickFirstButton() }
                ) {
                    Text(text = firstButton.label)
                }
                Text(
                    text = firstButton.descriptors[firstIdx],
                    modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.changeState(0, secondButton.descriptorsCount) }
                ) {
                    Text(text = secondButton.label)
                }
                Text(
                    text = secondButton.getByIdx(homeUiState?.labelIdxValue),
                    modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onClickThirdButton() }
                ) {
                    Text(text = thirdButton.label)
                }
                Text(
                    text = thirdButton.descriptors[thirdIdx],
                    modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}