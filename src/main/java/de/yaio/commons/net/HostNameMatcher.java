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

import java.util.List;

import org.apache.commons.lang3.StringUtils;


/** 
 * utils for hostName-matching
 * 
 * @FeatureDomain                Utils
 * @package                      de.yaio.commons.net
 * @author                       Michael Schreiner <michael.schreiner@your-it-fellow.de>
 * @category                     utils
 * @copyright                    Copyright (c) 2014, Michael Schreiner
 * @license                      http://mozilla.org/MPL/2.0/ Mozilla Public License 2.0
 */
public class HostNameMatcher {
    private List<String> hostRegExList;

    /** 
     * creates an HostNameMatcher
     * @FeatureDomain                Tools - URL-Handling
     * @FeatureResult                returnValues - HostNameMatcher
     * @FeatureKeywords              URL-Handling
     * @param hostRegExList          list of regex to match hostnames 
     */
    public HostNameMatcher(final List<String> hostRegExList) {
        this.hostRegExList = hostRegExList;
    }

    /** 
     * checks if the hostname matches one of the hostRegExList
     * @FeatureDomain                Tools - URL-Handling
     * @FeatureResult                returnValues - true/false if it matches hostRegExList
     * @FeatureKeywords              URL-Handling
     * @param hostName               hostname to check
     * @return                       true/false if it matches
     */
    public boolean isInList(final String hostName) {
        for(String hostRegex : hostRegExList) {
            if (StringUtils.isBlank(hostRegex)) {
                continue;
            }
            if(hostName.matches(hostRegex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * list of possible hostname-regex to match
     * @return                        hostRegExList
     */
    public List<String> getHostRegExList() {
        return this.hostRegExList;
    }
}
