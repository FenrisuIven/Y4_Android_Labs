package com.example.lb1.model

class LabeledButton(
  val label: String,
  val descriptors: Array<String>,
) {
  val descriptorsCount: Int
    get() = descriptors.size

  fun incDescriptorIdx(descriptorIdx: Int): Int {
    var newIdx = descriptorIdx
    if (newIdx + 1 > descriptors.size - 1) {
      newIdx = 0;
    } else {
      newIdx += 1
    }
    return newIdx;
  }

  fun getByIdx(idx: Int?): String {
    return descriptors[idx!!]
  }
}