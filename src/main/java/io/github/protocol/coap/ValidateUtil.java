/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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

import java.util.regex.Pattern;

public class ValidateUtil {

    private static final Pattern HOSTNAME_REGEX = Pattern.compile("^[a-z\\dA-Z_.\\-]+$");

    private static final Pattern LIKE_IP_REGEX = Pattern.compile("^[\\d.]+$");

    public static boolean isNotHost(String host) {
        return !isHost(host);
    }

    public static boolean isHost(String host) {
        if (LIKE_IP_REGEX.matcher(host).matches()) {
            return isValidIp(host);
        }
        return HOSTNAME_REGEX.matcher(host).matches();
    }

    public static boolean isValidIp(String ip) {
        return RegexUtil.IPV4_PATTERN.matcher(ip).matches();
    }

    public static boolean isNotPort(String port) {
        return !isPort(port);
    }

    public static boolean isPort(String port) {
        try {
            int aux = Integer.parseInt(port);
            return aux > 0 && aux < 65536;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNotNumber(String str) {
        return !isNumber(str);
    }

    public static boolean isNumber(String str) {
        return RegexUtil.NUMBER_PATTERN.matcher(str).matches();
    }
}
