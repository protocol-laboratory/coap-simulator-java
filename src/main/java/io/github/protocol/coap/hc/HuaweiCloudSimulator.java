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

package io.github.protocol.coap.hc;

import io.github.protocol.coap.BasePlatformSimulator;
import io.github.protocol.coap.CoapConfig;
import io.github.protocol.coap.CoapSimulator;
import io.github.protocol.coap.HexUtil;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.elements.Connector;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.pskstore.AdvancedSinglePskStore;

import java.net.InetSocketAddress;

public class HuaweiCloudSimulator extends BasePlatformSimulator {

    private final HuaweiCloudConfig huaweiCloudConfig;

    @Override
    public Endpoint getTlsEndPoint() {
        String psk = huaweiCloudConfig.getPsk();
        String verifyCode = huaweiCloudConfig.getVerifyCode();
        if (psk != null && verifyCode != null) {
            Configuration config = Configuration.getStandard();
            config.set(DtlsConfig.DTLS_RECOMMENDED_CIPHER_SUITES_ONLY, false);
            config.setAsList(DtlsConfig.DTLS_CIPHER_SUITES,
                    CipherSuite.TLS_PSK_WITH_AES_128_CCM_8,
                    CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256);
            DtlsConnectorConfig.Builder connectorConfigBuilder =
                    new DtlsConnectorConfig.Builder(config);
            connectorConfigBuilder.setAdvancedPskStore(
                    new AdvancedSinglePskStore(verifyCode, HexUtil.hexToByteArray(psk)));
            connectorConfigBuilder.setAddress(new InetSocketAddress(0));
            DtlsConnectorConfig dtlsConnectorConfig = connectorConfigBuilder.build();
            Connector connector = new DTLSConnector(dtlsConnectorConfig);
            return CoapEndpoint.builder()
                    .setConnector(connector)
                    .setConfiguration(config).build();
        } else {
            throw new NullPointerException("invalid psk or verify code");
        }
    }

    public HuaweiCloudSimulator(HuaweiCloudConfig huaweiCloudConfig, CoapConfig coapConfig) {
        super(coapConfig);
        this.coapConfig.setPort(coapConfig.isDtls() ? "5684" : "5683");
        this.huaweiCloudConfig = huaweiCloudConfig;
        init();
    }

    public String post(String timeoutSeconds) {
        return coapSimulator.send("POST", "", timeoutSeconds, MediaTypeRegistry.APPLICATION_OCTET_STREAM);
    }

    public String deviceRegistry() {
        coapConfig.setPath("t/r");
        coapConfig.setQueryParam(String.format("ep=%s", huaweiCloudConfig.getVerifyCode()));
        return post("5");
    }

    public CoapSimulator getCoapSimulator() {
        return coapSimulator;
    }
}
