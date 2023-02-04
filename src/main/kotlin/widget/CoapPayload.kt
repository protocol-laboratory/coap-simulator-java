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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.protocol.coap.model.Payload
import io.github.protocol.coap.model.TextType
import mediaType
import method
import simulator
import timeoutSeconds
import widget.component.CheckboxInput
import widget.component.RowPaddingButton

@Composable
fun CoapPayload(): Payload {

    val isStr = mutableStateOf(false)
    val isHex = mutableStateOf(true)

    val payload = mutableStateOf("")
    Row(Modifier.fillMaxHeight(0.1f).fillMaxWidth(1f).absolutePadding(10.dp, 0.dp, 10.dp, 0.dp)) {
        Column(Modifier.fillMaxHeight(1f).fillMaxWidth(0.3f).absolutePadding(0.dp, 5.dp, 0.dp, 0.dp)) {
            Text(text = "Payload Type:", fontSize = 18.sp)
        }
        Column(Modifier.fillMaxHeight(1f).fillMaxWidth(0.2f)) {
            CheckboxInput(isStr, "str")
        }
        Column(Modifier.fillMaxHeight(1f).fillMaxWidth(0.2f)) {
            CheckboxInput(isHex, "hex")
        }
    }
    Row {
        Column(Modifier.fillMaxHeight(1f).fillMaxWidth(0.2f).absolutePadding(10.dp, 0.dp, 10.dp, 0.dp)) {
            Text(text = "Payload:", fontSize = 18.sp)
        }
        Column(Modifier.fillMaxHeight(1f).fillMaxWidth(1f)) {
            OutlinedTextField(
                value = payload.value,
                onValueChange = {
                    payload.value = it
                },
                modifier = Modifier.fillMaxHeight(0.8f).fillMaxWidth(1f).absolutePadding(0.dp, 20.dp, 20.dp, 0.dp)
            )
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
                Text(text = "send", fontSize = 12.sp)
            }
        }
    }
    return Payload(TextType.HEX, payload.value)
}
