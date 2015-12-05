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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.SubnetUtils;

/** 
 * utils for ip-address-matching
 * 
 * @FeatureDomain                Utils
 * @package                      de.yaio.commons.net
 * @author                       Michael Schreiner <michael.schreiner@your-it-fellow.de>
 * @category                     utils
 * @copyright                    Copyright (c) 2014, Michael Schreiner
 * @license                      http://mozilla.org/MPL/2.0/ Mozilla Public License 2.0
 */
public class IpAddressMatcher {
    private List<SubnetUtils> ipList;

    /** 
     * creates an HostNameMatcher
     * @FeatureDomain                Tools - URL-Handling
     * @FeatureResult                returnValues - IpAddressMatcher
     * @FeatureKeywords              URL-Handling
     * @param ipList                 list of ip-addresses with/without netmask
     */
    public IpAddressMatcher(List<String> ipList) {
        this.ipList = new ArrayList<SubnetUtils>();
        for(String ip : ipList) {
            if (StringUtils.isBlank(ip)) {
                continue;
            }
            SubnetUtils ipMatcher;
            if(ip.indexOf("/") > 0) {
                ipMatcher = new SubnetUtils(ip);
            } else {
                ipMatcher = new SubnetUtils(ip + "/32");
            }
            ipMatcher.setInclusiveHostCount(true);
            this.ipList.add(ipMatcher);
        }
    }

    /** 
     * checks if the ipAddress matches one of the netmasks from ipList
     * @FeatureDomain                Tools - URL-Handling
     * @FeatureResult                returnValues - true/false if it matches netmasks from ipList
     * @FeatureKeywords              URL-Handling
     * @param ipAddress              ipAddress to check
     * @return                       true/false if it matches netmasks from ipList
     */
    public boolean isInList(final String ipAddress) {
        for(SubnetUtils address : ipList) {
            if(address.getInfo().isInRange(ipAddress)) {
                return true;
            }
        }
        return false;
    }

    /**
     * list of possible netmasks to match
     * @return                        ipAddress
     */
    public List<SubnetUtils> getIpList() {
        return this.ipList;
    }
}
