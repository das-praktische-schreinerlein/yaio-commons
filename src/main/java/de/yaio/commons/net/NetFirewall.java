/** 
 * common yaio-utils
 * 
 * @FeatureDomain                Utils 
 * @author                       Michael Schreiner <michael.schreiner@your-it-fellow.de>
 * @category                     utils
 * @copyright                    Copyright (c) 2014, Michael Schreiner
 * @license                      http://mozilla.org/MPL/2.0/ Mozilla Public License 2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package de.yaio.commons.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import de.yaio.commons.io.IOExceptionWithCause;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/** 
 * a NetFirewall
 */
public class NetFirewall {
    protected final IpAddressMatcher ipBlackList;
    protected final IpAddressMatcher ipWhiteList;
    protected final HostNameMatcher hostBlackList;
    protected final HostNameMatcher hostWhiteList;
    protected final ProtocolMatcher protocolWhiteList;

    /** 
     * creates an NetFirewall
     * @param ipBlackList            ips which are blacklisted
     * @param ipWhiteList            ips which are whitelisted: overrides blacklist
     * @param hostBlackList          hostnames which are blacklisted
     * @param hostWhiteList          hostnames which are whitelisted: overrides blacklist
     * @param protocolWhiteList      allowed protocols
     */
    public NetFirewall(final List<String> ipBlackList,
                       final List<String> ipWhiteList,
                       final List<String> hostBlackList,
                       final List<String> hostWhiteList,
                       final List<String> protocolWhiteList) {

        this.ipBlackList = new IpAddressMatcher(ipBlackList);
        this.ipWhiteList = new IpAddressMatcher(ipWhiteList);
        this.hostBlackList = new HostNameMatcher(hostBlackList);
        this.hostWhiteList = new HostNameMatcher(hostWhiteList);
        this.protocolWhiteList = new ProtocolMatcher(protocolWhiteList);
    }

    /** 
     * creates an NetFirewall
     * @param ipBlackList            ips which are blacklisted
     * @param ipWhiteList            ips which are whitelisted: overrides blacklist
     * @param hostBlackList          hostnames which are blacklisted
     * @param hostWhiteList          hostnames which are whitelisted: overrides blacklist
     * @param protocolWhiteList      allowed protocols
     */
    public NetFirewall(final IpAddressMatcher ipBlackList,
                       final IpAddressMatcher ipWhiteList,
                       final HostNameMatcher hostBlackList,
                       final HostNameMatcher hostWhiteList,
                       final ProtocolMatcher protocolWhiteList) {

        this.ipBlackList = ipBlackList;
        this.ipWhiteList = ipWhiteList;
        this.hostBlackList = hostBlackList;
        this.hostWhiteList = hostWhiteList;
        this.protocolWhiteList = protocolWhiteList;
    }

    /** 
     * checks if ip is forbidden
     * @param ipAddress              ip-address to check
     * @return                       true/false if it is forbidden
     */
    public boolean isInIPBlackList(final String ipAddress) {
        return ipBlackList.isInList(ipAddress);
    }

    /** 
     * checks if ip is allowed
     * @param ipAddress              ip-address to check
     * @return                       true/false if it is allowed
     */
    public boolean isInIPWhiteList(final String ipAddress) {
        return ipWhiteList.isInList(ipAddress);
    }

    /** 
     * checks if hostname is forbidden
     * @param host                   hostname to check
     * @return                       true/false if it is forbidden
     */
    public boolean isInHostBlackList(final String host) {
        return hostBlackList.isInList(host);
    }

    /** 
     * checks if hostname is allowed
     * @param host                   hostname to check
     * @return                       true/false if it is allowed
     */
    public boolean isInHostWhiteList(final String host) {
        return hostWhiteList.isInList(host);
    }

    /** 
     * checks if protocol is allowed
     * @param protocol               protocol to check
     * @return                       true/false if it is allowed
     */
    public boolean isInProtocolList(final String protocol) {
        return protocolWhiteList.isInList(protocol);
    }

    /** 
     * checks if url is allowed
     * @param url                    url to check (check protocoll, hostname and ip)
     * @return                       true/false if it is allowed
     * @throws MalformedURLException parsing the url
     * @throws UnknownHostException  parsing the url
     */
    public boolean isAllowed(final URL url) throws UnknownHostException, MalformedURLException {
        // check protocol
        if (!isInProtocolList(url.getProtocol())) {
            return false;
        }

        // check inetAddr
        InetAddress inetAddr = NetUtils.parseAddress(url.getHost());
        String hostName = inetAddr.getHostName();
        String ip = inetAddr.getHostAddress();
        if (isInHostBlackList(hostName) || isInIPBlackList(ip)) {
            // blacklisted: but check for override by whitelist
            if (isInHostWhiteList(hostName) || isInIPWhiteList(ip)) {
                return true;
            }

            // blacklisted
            return false;
        }

        return true;
    }

    /** 
     * checks if url is allowed
     * @param url                    url to check (check protocoll, hostname and ip)
     * @return                       true/false if it is allowed
     * @throws MalformedURLException parsing the url
     * @throws UnknownHostException  parsing the url
     */
    public boolean isUrlAllowed(final String url) throws UnknownHostException, MalformedURLException {
        URL iUrl = new URL(url);
        return isAllowed(iUrl);
    }

    /**
     * checks if url is allowed
     * @param url                    url to check (check protocol, hostname and ip)
     * @throws PermissionException   not allowed
     * @throws IOExceptionWithCause  parsing the url
     */
    public void throwExceptionIfNotAllowed(final String url) throws IOExceptionWithCause, PermissionException {
        try {
            if (!isUrlAllowed(url)) {
                throw new PermissionException("request not allowed by NetFirewall", url, new IOException("request for url:" + url
                        + " not allowed by NetFirewall:"
                        + new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString()));
            }
        } catch (UnknownHostException ex) {
            throw new IOExceptionWithCause("unknown host", url, ex);
        } catch (MalformedURLException ex) {
            throw new IOExceptionWithCause("malformed url", url, ex);
        }
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
