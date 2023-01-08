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

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import io.github.protocol.coap.CoapConfig
import io.github.protocol.coap.CoapConfigStorage
import io.github.protocol.coap.CoapSimulator
import widget.component.CheckboxInput
import widget.component.DropdownList
import widget.component.RowPaddingButton
import widget.config.ConfigItemPort
import widget.config.ConfigItemString

val config = mutableStateOf(CoapConfigStorage.getCoapConfig())

val coapHost = mutableStateOf(config.value.host)
val coapPort = mutableStateOf(config.value.port)
val coapDtls = mutableStateOf(config.value.isDtls)
val coapPath = mutableStateOf(config.value.path)
val coapQueryParam = mutableStateOf(config.value.queryParam)

@Preview
@Composable
fun CoapCore() {
    val timeoutSeconds = mutableStateOf("5")
    val method = mutableStateOf("GET")
    val mediaType = mutableStateOf("TEXT_PLAIN")
    val payload = mutableStateOf("")
    var simulator: CoapSimulator? by remember { mutableStateOf(null) }

    Column {
        Text("Coap Core: ", fontSize = 40.sp)
        ConfigItemString(coapHost, "host")
        ConfigItemPort(coapPort, "port", mutableStateOf(""))
        CheckboxInput(coapDtls, "dtls")
        ConfigItemString(coapPath, "coap path")
        ConfigItemString(coapQueryParam, "query param")
        Row {
            RowPaddingButton(
                onClick = {
                    val coapConfig = CoapConfig(
                        coapHost.value,
                        coapPort.value,
                        coapDtls.value,
                        coapPath.value,
                        coapQueryParam.value,
                    )
                    simulator = CoapSimulator(coapConfig)
                    addMessage("Coap simulator is started")
                }
            ) {
                Text(text = "connect", fontSize = 12.sp)
            }
            RowPaddingButton(
                onClick = {
                    if (simulator != null) {
                        simulator!!.stop()
                        simulator = null
                        addMessage("Coap simulator is stopped")
                    }
                }
            ) {
                Text(text = "disconnect", fontSize = 12.sp)
            }
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
        Row {
            ConfigItemString(timeoutSeconds, "timeoutSeconds")
            DropdownList(method, listOf("GET", "POST", "PUT", "DELETE"), "method")
        }
        DropdownList(
            mediaType,
            listOf(
                "TEXT_PLAIN",
                "APPLICATION_LINK_FORMAT",
                "APPLICATION_XML",
                "APPLICATION_OCTET_STREAM",
                "APPLICATION_EXI",
                "APPLICATION_JSON",
                "APPLICATION_CBOR"
            ),
            "mediaType"
        )
        ConfigItemString(payload, "payload", singleLine = false)
    }
}
