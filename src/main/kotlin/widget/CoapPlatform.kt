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
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import module.CoapPlatformEnum
import widget.coapplateform.CoapPlatformHuaweiCloud
import widget.component.DropdownList

val idx = mutableStateOf(CoapPlatformEnum.HuaweiCloud)
@Preview
@Composable
fun CoapPlatform() {
    Column {
        Head(idx)
        when (idx.value.name) {
            CoapPlatformEnum.HuaweiCloud.name -> {
                CoapPlatformHuaweiCloud()
            }
        }
    }
}

@Composable
fun Head(idx: MutableState<CoapPlatformEnum>) {
    Row {
        Column(modifier = Modifier.fillMaxWidth(0.4f)) {
            Text("Coap platform: ", modifier = Modifier.fillMaxWidth(1f).absolutePadding(10.dp, 25.dp, 0.dp, 5.dp), fontSize = 25.sp)
        }
        Column(modifier = Modifier.fillMaxWidth(1f).absolutePadding(0.dp, 0.dp, 10.dp, 0.dp)) {
            DropdownList(mutableStateOf(idx.value.name), listOf("HuaweiCloud", "AliCloud"), "Clouds")
        }
    }
}
