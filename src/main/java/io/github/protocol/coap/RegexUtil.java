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

public class RegexUtil {

    public static final String NUMBER_REG = "[0-9]+";

    public static final String WORDS_REG = "\\w+";

    public static final String IPV4_REGEX =
            "\\A(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z";

    public static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REG);

    public static final Pattern WORDS_PATTERN = Pattern.compile(WORDS_REG);

    public static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

}
