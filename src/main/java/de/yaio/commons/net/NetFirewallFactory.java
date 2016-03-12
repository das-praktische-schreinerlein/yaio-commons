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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/** 
 * Utils for NetFirewall
 */
public class NetFirewallFactory {
    
    /** 
     * creates a secured NetFirewall by netFirewallConfig
     * @param netFirewallConfig      netFirewallConfig
     * @return                       NetFirewall
     */
    public static NetFirewall creatNetFirewall(final NetFirewallConfig netFirewallConfig) {
        // use defaults if wished
        if (netFirewallConfig.isFlgUseDefault()) {
            return createSecureWebshotFirewall();
        }
        
        // configure
        IpAddressMatcher ipBlackList;
        HostNameMatcher hostBlackList; 
        ProtocolMatcher protocolWhiteList;
        if (netFirewallConfig.isFlgUseDefaultIPBlackList()) {
            ipBlackList = createIPAdressMatcherForLocalNets();
        } else {
            ipBlackList = new IpAddressMatcher(
                            Arrays.asList(netFirewallConfig.getIpBlackList().split(",")));
        }
        if (netFirewallConfig.isFlgUseDefaultHostBlackList()) {
            hostBlackList = createHostNameMatcherForLocalHosts();
        } else {
            hostBlackList = new HostNameMatcher(
                            Arrays.asList(netFirewallConfig.getHostBlackList().split(",")));
        }
        if (netFirewallConfig.isFlgUseDefaultProtocolWhiteList()) {
            protocolWhiteList = createProtocolMatcherForWebProtocols();
        } else {
            protocolWhiteList = new ProtocolMatcher(
                            Arrays.asList(netFirewallConfig.getProtocolWhiteList().split(",")));
        }
        
        HostNameMatcher hostWhiteList = new HostNameMatcher(
                        Arrays.asList(netFirewallConfig.getHostWhiteList().split(",")));
        IpAddressMatcher ipWhiteList = new IpAddressMatcher(
                        Arrays.asList(netFirewallConfig.getIpWhiteList().split(",")));

        return new NetFirewall(ipBlackList, ipWhiteList, hostBlackList, hostWhiteList, protocolWhiteList);
    }
    
    /** 
     * creates a secured NetFirewall (only http, https, ftp and all localnets are blacklisted)
     * @param ipBlackList            comma separated ips which are blacklisted
     * @param ipWhiteList            comma separated ips which are whitelisted: overrides blacklist
     * @param hostBlackList          comma separated hostnames which are blacklisted
     * @param hostWhiteList          comma separated hostnames which are whitelisted: overrides blacklist
     * @param protocolWhiteList      comma separated allowed protocols
     * @return                       secure NetFirewall
     */
    public static NetFirewall creatNetFirewall(final String ipBlackList,
                                               final String ipWhiteList,
                                               final String hostBlackList,
                                               final String hostWhiteList,
                                               final String protocolWhiteList) {
        return new NetFirewall(Arrays.asList(ipBlackList.split(",")),
            Arrays.asList(ipWhiteList.split(",")),
            Arrays.asList(hostBlackList.split(",")),
            Arrays.asList(hostWhiteList.split(",")),
            Arrays.asList(protocolWhiteList.split(",")));
    }
    
    /** 
     * creates a secured NetFirewall (only http, https, ftp and all localnets are blacklisted)
     * @return                       secure NetFirewall
     */
    public static NetFirewall createSecureWebshotFirewall() {
        IpAddressMatcher ipBlackList = createIPAdressMatcherForLocalNets();
        IpAddressMatcher ipWhiteList = new IpAddressMatcher(new ArrayList<String>());
        HostNameMatcher hostBlackList = createHostNameMatcherForLocalHosts(); 
        HostNameMatcher hostWhiteList = new HostNameMatcher(new ArrayList<String>());
        ProtocolMatcher protocolWhiteList = createProtocolMatcherForWebProtocols();
        return new NetFirewall(ipBlackList, ipWhiteList, hostBlackList, hostWhiteList, protocolWhiteList);
    }
    
    /** 
     * creates a IpAddressMatcher for all localnets
     * @return                       IpAddressMatcher for all localnets
     */
    public static IpAddressMatcher createIPAdressMatcherForLocalNets() {
        List<String> ipList = Arrays.asList(
            "127.0.0.0/8",
            "10.0.0.0/8",
            "172.16.0.0/12",
            "192.168.0.0/16"
        );
        return new IpAddressMatcher(ipList);
    }

    /** 
     * creates a HostNameMatcher for all localhost-names
     * @return                       IpAddressMatcher for all localhost-names
     */
    public static HostNameMatcher createHostNameMatcherForLocalHosts() {
        List<String> hostList = Arrays.asList(
            "localhost", 
            ".*\\.local", 
            ".*\\.localnet", 
            ".*\\.intern"
        );
        return new HostNameMatcher(hostList);
    }

    /** 
     * creates a ProtocolMatcher for common web-protocols
     * @return                       ProtocolMatcher for common web-protocols
     */
    public static ProtocolMatcher createProtocolMatcherForWebProtocols() {
        List<String> hostList = Arrays.asList(
            "http", "https", "ftp"
        );
        return new ProtocolMatcher(hostList);
    }
}
