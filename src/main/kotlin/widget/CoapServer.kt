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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import coapHost
import coapPort
import widget.config.ConfigItemString

@Composable
fun CoapServer() {
    Row(Modifier.fillMaxWidth(1f)) {
        Column(Modifier.fillMaxWidth(0.3f).fillMaxHeight(1f)) {
            Text("SERVER:", color = Color(0xFF000080), fontSize = 3.em, textAlign = TextAlign.Left)
        }
        Column(Modifier.fillMaxWidth(0.5f)) {
            ConfigItemString(coapHost, "HOST")
        }
        Column(Modifier.fillMaxWidth(1f)) {
            ConfigItemString(coapPort, "PORT")
        }
    }
}
