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

import java.util.Objects;

public class CoapConfig {

    public static final String DEFAULT_HOST = "localhost";

    public static final String DEFAULT_PORT = "5683";

    public static final String DEFAULT_PATH = "";

    public static final String DEFAULT_QUERY_PARAM = "";

    private String host;

    private String port;

    private boolean dtls;

    private String path;

    private String queryParam;

    public CoapConfig() {
        this(DEFAULT_HOST, DEFAULT_PORT, false, DEFAULT_PATH, DEFAULT_QUERY_PARAM);
    }

    public CoapConfig(String host, String port, boolean dtls, String path, String queryParam) {
        this.host = host;
        this.port = port;
        this.dtls = dtls;
        this.path = path;
        this.queryParam = queryParam;
    }

    public String getHost() {
        return Objects.requireNonNullElse(host, DEFAULT_HOST);
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return Objects.requireNonNullElse(port, DEFAULT_PORT);
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isDtls() {
        return dtls;
    }

    public void setDtls(boolean dtls) {
        this.dtls = dtls;
    }

    public String getPath() {
        return Objects.requireNonNullElse(path, DEFAULT_PATH);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQueryParam() {
        return Objects.requireNonNullElse(queryParam, DEFAULT_QUERY_PARAM);
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }
}
