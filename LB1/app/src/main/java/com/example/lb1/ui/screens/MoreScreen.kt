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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MoreScreen(
  modifier: Modifier
) {
  var firstButtonLabelIdx by rememberSaveable{ mutableIntStateOf(0) }
  var secondButtonLabelIdx by rememberSaveable{ mutableIntStateOf(0) }

  val firstButtonLabelsArray = arrayOf("first", "second", "third", "last");
  val secondButtonLabelsArray = arrayOf("1st", "2nd", "3rd", "Nth");

  fun firstButtonOnClickHandler() {
    if (firstButtonLabelIdx + 1 > firstButtonLabelsArray.size - 1) {
      firstButtonLabelIdx = 0
      return
    }
    firstButtonLabelIdx += 1;
  }

  fun secondButtonOnClickHandler() {
    if (secondButtonLabelIdx + 1 > secondButtonLabelsArray.size - 1) {
      secondButtonLabelIdx = 0
      return
    }
    secondButtonLabelIdx += 1;
  }

  Column(
    modifier = modifier.padding(0.dp, 16.dp)
  ) {
    Text(
      text = "More Screen",
      modifier = Modifier.align(Alignment.CenterHorizontally)
    )

    Row(
      modifier = Modifier.padding(16.dp, 8.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      Button(
        modifier = Modifier.fillMaxWidth().weight(1f),
        onClick = { firstButtonOnClickHandler() }
      ) {
        Text(
          text = "Button 1"
        )
      }
      Text(
        text = "Button 1 descriptor: ${firstButtonLabelsArray[firstButtonLabelIdx]}",
        modifier = Modifier.align(Alignment.CenterVertically).weight(2f)
      )
    }

    Row(
      modifier = Modifier.padding(16.dp, 0.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      Button(
        modifier = Modifier.fillMaxWidth().weight(1f),
        onClick = { secondButtonOnClickHandler() }
      ) {
        Text(
          text = "Button 2"
        )
      }
      Text(
        text = "Button 2 descriptor: ${secondButtonLabelsArray[secondButtonLabelIdx]}",
        modifier = Modifier.align(Alignment.CenterVertically).weight(2f)
      )
    }
  }
}