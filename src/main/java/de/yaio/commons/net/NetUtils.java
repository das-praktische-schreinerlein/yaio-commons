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

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/** 
 * utils for ip-addess-matching
 */
public class NetUtils {

    /** 
     * parse IPAddress of the hostName
     * @param hostname               hostName to parse
     * @return                       corresponding IPAddress
     */
    public static InetAddress parseAddress(String hostname) {
        try {
            return InetAddress.getByName(hostname);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Failed to parse address" + hostname, e);
        }
    }
    
    /** 
     * parse IPAddress of the url
     * @param url                    url to parse
     * @return                       corresponding IPAddress
     * @throws MalformedURLException if url is invalid
     */
    public static InetAddress parseAddressFromUrl(String url) throws MalformedURLException {
        String myUrl = url.toLowerCase();
        if (!myUrl.startsWith("http://") && !myUrl.startsWith("https://") && !myUrl.startsWith("ftp://")) {
            throw new IllegalArgumentException("Illegal protocoll (only: http, https, ftp) for url:" + myUrl);
        }
        URL iUrl = new URL(myUrl);
        return parseAddress(iUrl.getHost());
    }
}
