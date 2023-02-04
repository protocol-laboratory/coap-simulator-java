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

package widget.coapplateform

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coapDtls
import coapHost
import coapPath
import coapPort
import coapQueryParam
import io.github.protocol.coap.CoapConfig
import io.github.protocol.coap.CoapSimulator
import psk
import simulator
import verifyCode
import widget.CoapPayload
import widget.addMessage
import widget.component.RowPaddingButton
import widget.config.ConfigItemString

@Composable
fun CoapPlatformHuaweiCloud() {
    ConfigItemString(
        verifyCode,
        "verifyCode",
        modifier = Modifier.fillMaxWidth(1f).absolutePadding(10.dp, 3.dp, 10.dp, 0.dp)
    )
    ConfigItemString(psk, "PSK", modifier = Modifier.fillMaxWidth(1f).absolutePadding(10.dp, 0.dp, 10.dp, 0.dp))
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
    }
    CoapPayload()
    RowPaddingButton(
        onClick = {
        }
    ) {
        Text(text = "send", fontSize = 12.sp)
    }
}
