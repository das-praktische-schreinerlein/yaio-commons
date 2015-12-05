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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/** 
 * utils for NetFirewall
 * 
 * @FeatureDomain                Utils
 * @package                      de.yaio.commons.net
 * @author                       Michael Schreiner <michael.schreiner@your-it-fellow.de>
 * @category                     collaboration
 * @copyright                    Copyright (c) 2014, Michael Schreiner
 * @license                      http://mozilla.org/MPL/2.0/ Mozilla Public License 2.0
 */
public class NetFirewallConfig {
    private String ipBlackList = "";
    private String ipWhiteList = "";
    private String hostBlackList = "";
    private String hostWhiteList = "";
    private String protocolWhiteList = "";
    private boolean flgUseDefault = true;
    private boolean flgUseDefaultIPBlackList = true;
    private boolean flgUseDefaultHostBlackList = true;
    private boolean flgUseDefaultProtocolWhiteList = true;

    /**
     * @return the {@link NetFirewallConfig#ipBlackList}
     */
    public String getIpBlackList() {
        return this.ipBlackList;
    }
    /**
     * @param ipBlackList the {@link NetFirewallConfig#ipBlackList} to set
     */
    public void setIpBlackList(String ipBlackList) {
        this.ipBlackList = ipBlackList;
    }
    /**
     * @return the {@link NetFirewallConfig#ipWhiteList}
     */
    public String getIpWhiteList() {
        return this.ipWhiteList;
    }
    /**
     * @param ipWhiteList the {@link NetFirewallConfig#ipWhiteList} to set
     */
    public void setIpWhiteList(String ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }
    /**
     * @return the {@link NetFirewallConfig#hostBlackList}
     */
    public String getHostBlackList() {
        return this.hostBlackList;
    }
    /**
     * @param hostBlackList the {@link NetFirewallConfig#hostBlackList} to set
     */
    public void setHostBlackList(String hostBlackList) {
        this.hostBlackList = hostBlackList;
    }
    /**
     * @return the {@link NetFirewallConfig#hostWhiteList}
     */
    public String getHostWhiteList() {
        return this.hostWhiteList;
    }
    /**
     * @param hostWhiteList the {@link NetFirewallConfig#hostWhiteList} to set
     */
    public void setHostWhiteList(String hostWhiteList) {
        this.hostWhiteList = hostWhiteList;
    }
    /**
     * @return the {@link NetFirewallConfig#protocolWhiteList}
     */
    public String getProtocolWhiteList() {
        return this.protocolWhiteList;
    }
    /**
     * @param protocolWhiteList the {@link NetFirewallConfig#protocolWhiteList} to set
     */
    public void setProtocolWhiteList(String protocolWhiteList) {
        this.protocolWhiteList = protocolWhiteList;
    }
    /**
     * @return the {@link NetFirewallConfig#flgUseDefault}
     */
    public boolean isFlgUseDefault() {
        return this.flgUseDefault;
    }
    /**
     * @param flgUseDefault the {@link NetFirewallConfig#flgUseDefault} to set
     */
    public void setFlgUseDefault(boolean flgUseDefault) {
        this.flgUseDefault = flgUseDefault;
    }
    /**
     * @return the {@link NetFirewallConfig#flgUseDefaultIPBlackList}
     */
    public boolean isFlgUseDefaultIPBlackList() {
        return this.flgUseDefaultIPBlackList;
    }
    /**
     * @param flgUseDefaultIPBlackList the {@link NetFirewallConfig#flgUseDefaultIPBlackList} to set
     */
    public void setFlgUseDefaultIPBlackList(boolean flgUseDefaultIPBlackList) {
        this.flgUseDefaultIPBlackList = flgUseDefaultIPBlackList;
    }
    /**
     * @return the {@link NetFirewallConfig#flgUseDefaultHostBlackList}
     */
    public boolean isFlgUseDefaultHostBlackList() {
        return this.flgUseDefaultHostBlackList;
    }
    /**
     * @param flgUseDefaultHostBlackList the {@link NetFirewallConfig#flgUseDefaultHostBlackList} to set
     */
    public void setFlgUseDefaultHostBlackList(boolean flgUseDefaultHostBlackList) {
        this.flgUseDefaultHostBlackList = flgUseDefaultHostBlackList;
    }
    /**
     * @return the {@link NetFirewallConfig#flgUseDefaultProtocolWhiteList}
     */
    public boolean isFlgUseDefaultProtocolWhiteList() {
        return this.flgUseDefaultProtocolWhiteList;
    }
    /**
     * @param flgUseDefaultProtocolWhiteList the {@link NetFirewallConfig#flgUseDefaultProtocolWhiteList} to set
     */
    public void setFlgUseDefaultProtocolWhiteList(boolean flgUseDefaultProtocolWhiteList) {
        this.flgUseDefaultProtocolWhiteList = flgUseDefaultProtocolWhiteList;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
