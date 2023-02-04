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

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import constant.PixelConst
import io.github.protocol.coap.CoapConfigStorage
import io.github.protocol.coap.CoapSimulator
import widget.CoapCore
import widget.CoapOutput
import widget.CoapPlatform
import widget.CoapServer

val config = mutableStateOf(CoapConfigStorage.getCoapConfig())

val coapHost = mutableStateOf(config.value.host)
val coapPort = mutableStateOf(config.value.port)
val coapDtls = mutableStateOf(config.value.isDtls)
val coapPath = mutableStateOf(config.value.path)
val coapQueryParam = mutableStateOf(config.value.queryParam)
var simulator: CoapSimulator? = null
val timeoutSeconds = mutableStateOf("5")
val method = mutableStateOf("GET")
val mediaType = mutableStateOf("TEXT_PLAIN")
val payload = mutableStateOf("")
val verifyCode = mutableStateOf("")
val psk = mutableStateOf("")
@Composable
@Preview
fun App() {

    Row {
        Column(Modifier.fillMaxHeight(1f).fillMaxWidth(0.8f).border(width = 1.dp, Color.Gray)) {
            Row(Modifier.fillMaxHeight(0.1f).fillMaxWidth(1f).border(width = 1.dp, Color.Gray)) {
                CoapServer()
            }
            Row(Modifier.fillMaxHeight(1f).fillMaxWidth(1f).border(width = 1.dp, Color.Gray)) {
                Column(Modifier.fillMaxHeight(1f).fillMaxWidth(0.5f).border(width = 1.dp, Color.Gray)) {
                    CoapCore()
                }
                Column(Modifier.fillMaxHeight(1f).fillMaxWidth(1f).border(width = 1.dp, Color.Gray)) {
                    CoapPlatform()
                }
            }
        }
        Column(Modifier.fillMaxHeight(1f).fillMaxWidth(1f).border(width = 1.dp, Color.Gray)) {
            CoapOutput()
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Coap Simulator",
        state = rememberWindowState(size = PixelConst.appSize)
    ) {
        App()
    }
}
