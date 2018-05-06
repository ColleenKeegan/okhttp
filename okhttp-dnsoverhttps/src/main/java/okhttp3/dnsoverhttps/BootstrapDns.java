/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package okhttp3.dnsoverhttps;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okhttp3.Dns;

/**
 * Internal Bootstrap DNS implementation for handling initial connection to DNS over HTTPS server.
 *
 * Returns hardcoded results for the known host.
 */
public class BootstrapDns implements Dns {
  private final String dnsHost;
  private final List<InetAddress> dnsServers;

  public BootstrapDns(String dnsHost, InetAddress... dnsServers) {
    this.dnsHost = dnsHost;
    this.dnsServers = Collections.unmodifiableList(Arrays.asList(dnsServers));
  }

  @Override public List<InetAddress> lookup(String hostname) throws UnknownHostException {
    if (hostname.equals(dnsHost)) {
      return dnsServers;
    }

    throw new UnknownHostException(hostname);
  }
}