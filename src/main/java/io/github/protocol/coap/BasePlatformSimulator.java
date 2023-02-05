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

import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.elements.config.Configuration;

public abstract class BasePlatformSimulator {

    protected CoapSimulator coapSimulator;

    protected CoapConfig coapConfig;

    public Endpoint getEndPoint() {
        CoapEndpoint.Builder builder = new CoapEndpoint.Builder();
        builder.setPort(0);
        builder.setConfiguration(Configuration.getStandard());
        return builder.build();
    }

    public abstract Endpoint getTlsEndPoint();

    public void init() {
        coapSimulator = new CoapSimulator(coapConfig);
        if (coapConfig.isDtls()) {
            coapSimulator.addServerEndPoint(getTlsEndPoint());
        } else {
            coapSimulator.addServerEndPoint(getEndPoint());
        }
    }

    protected BasePlatformSimulator(CoapConfig coapConfig) {
        this.coapConfig = coapConfig;
    }
}
