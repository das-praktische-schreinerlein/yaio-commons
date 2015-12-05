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
 * utils for protocol-matching
 * 
 * @FeatureDomain                Utils
 * @package                      de.yaio.commons.net
 * @author                       Michael Schreiner <michael.schreiner@your-it-fellow.de>
 * @category                     utils
 * @copyright                    Copyright (c) 2014, Michael Schreiner
 * @license                      http://mozilla.org/MPL/2.0/ Mozilla Public License 2.0
 */
public class ProtocolMatcher {
    private List<String> protocolList;

    /** 
     * creates an ProtocolMatcher
     * @FeatureDomain                Tools - URL-Handling
     * @FeatureResult                returnValues - ProtocolMatcher
     * @FeatureKeywords              URL-Handling
     * @param protocolList           list of protocols
     */
    public ProtocolMatcher(final List<String> protocolList) {
        this.protocolList = protocolList;
    }

    /** 
     * checks if the protocol matches one of the protocols
     * @FeatureDomain                Tools - URL-Handling
     * @FeatureResult                returnValues - true/false if it matches protocolList
     * @FeatureKeywords              URL-Handling
     * @param protocol               protocol to check
     * @return                       true/false if it matches protocolList
     */
    public boolean isInList(final String protocol) {
        for(String protocolDef : protocolList) {
            if (StringUtils.isBlank(protocolDef)) {
                continue;
            }
            if(protocolDef.matches(protocol)) {
                return true;
            }
        }
        return false;
    }

    /**
     * list of possible protocols to match
     * @return                        protocolList
     */
    public List<String> getProtocolList() {
        return this.protocolList;
    }
}
