/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */

package widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mediaType
import method
import module.LimitedList
import payload
import simulator
import timeoutSeconds
import widget.component.RowPaddingButton
import widget.component.TextLogger

val outputListUi: MutableState<List<String>> =
    mutableStateOf(listOf())
val outputMsgList = LimitedList(0, 500)

fun addMessage(msg: String) {
    outputMsgList.add(msg)
    outputListUi.value = outputMsgList.toList()
}

@Composable
fun CoapOutput() {

    Row(modifier = Modifier.fillMaxHeight(0.92f)) {
        Column(
            modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(0.95f).absolutePadding(10.dp, 20.dp, 10.dp, 0.dp)
                .border(width = 2.dp, color = Color.Gray)
        ) {
            TextLogger(outputListUi)
        }
    }

    Row(modifier = Modifier.fillMaxHeight(1f)) {
        Column(modifier = Modifier.fillMaxWidth(0.5f).absolutePadding(30.dp, 0.dp, 0.dp, 0.dp)) {
            RowPaddingButton(
                onClick = {
                    if (simulator == null) {
                        addMessage("Coap simulator is not started")
                    } else {
                        val result = simulator!!.send(
                            method.value,
                            payload.value,
                            timeoutSeconds.value,
                            mediaType.value,
                        )
                        addMessage(result)
                    }
                }
            ) {
                Text(text = "copy", fontSize = 12.sp)
            }
        }
        Column(modifier = Modifier.fillMaxWidth(1f).absolutePadding(30.dp, 0.dp, 0.dp, 0.dp)) {
            RowPaddingButton(
                onClick = {
                    if (simulator == null) {
                        addMessage("Coap simulator is not started")
                    } else {
                        val result = simulator!!.send(
                            method.value,
                            payload.value,
                            timeoutSeconds.value,
                            mediaType.value,
                        )
                        addMessage(result)
                    }
                }
            ) {
                Text(text = "clear", fontSize = 12.sp)
            }
        }
    }
}
