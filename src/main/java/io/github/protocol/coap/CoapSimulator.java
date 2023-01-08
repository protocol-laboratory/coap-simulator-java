/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.github.protocol.coap;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class CoapSimulator {

    private final CoapClient coapClient;

    public CoapSimulator(CoapConfig coapConfig) {
        String urlHead = coapConfig.isDtls() ? "coaps" : "coap";
        String url = urlHead + "://" + coapConfig.getHost() + ":" + coapConfig.getPort() + "/"
                + coapConfig.getPath() + "?" + coapConfig.getQueryParam();
        this.coapClient = new CoapClient(url);
    }

    public void stop() {
        this.coapClient.shutdown();
    }

    public String send(@NotNull String method, @NotNull String payload, String timeoutSeconds, String mediaType) {
        return this.send(method, payload, timeoutSeconds, MediaTypeRegistry.parse(mediaType));
    }

    public String send(String method, String payload, String timeoutSeconds, int mediaType) {
        this.coapClient.setTimeout(TimeUnit.SECONDS.toMillis(Integer.parseInt(timeoutSeconds)));
        CoapResponse coapResponse;
        try {
            switch (method) {
                case "GET" -> coapResponse = this.coapClient.get();
                case "POST" -> coapResponse = this.coapClient.post(payload, mediaType);
                case "PUT" -> coapResponse = this.coapClient.put(payload, mediaType);
                case "DELETE" -> coapResponse = this.coapClient.delete();
                default -> throw new IllegalArgumentException("Unsupported method: " + method);
            }
        } catch (Exception e) {
            return String.format("Error: %s", e.getMessage());
        }
        if (coapResponse == null) {
            return "Error: No response";
        }
        return "response is :" + coapResponse;
    }
}
