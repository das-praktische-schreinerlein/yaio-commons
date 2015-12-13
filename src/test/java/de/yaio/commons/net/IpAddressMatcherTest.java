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

import org.junit.Assert;
import org.junit.Test;

import de.yaio.commons.BaseTest;

/** 
 * test of the IPAddressMatcher logic<br>
 * test: parser, formatter
 * 
 * @FeatureDomain                Tests
 * @package                      de.yaio.commons.net
 * @author                       Michael Schreiner <michael.schreiner@your-it-fellow.de>
 * @category                     tests
 * @copyright                    Copyright (c) 2014, Michael Schreiner
 * @license                      http://mozilla.org/MPL/2.0/ Mozilla Public License 2.0
 */

public class IpAddressMatcherTest extends BaseTest {

    /** 
     * testobject for IpAddressMatcher
     * 
     * @FeatureDomain                TestObject
     * @package                      de.yaio.commons.net
     * @author                       Michael Schreiner <michael.schreiner@your-it-fellow.de>
     * @category                     tests
     * @copyright                    Copyright (c) 2014, Michael Schreiner
     * @license                      http://mozilla.org/MPL/2.0/ Mozilla Public License 2.0
     */
    public class IpAddressMatcheraTestObj extends IpAddressMatcher implements TestObj {
        /**
         * @param ipList            the iplist to test
         */
        public IpAddressMatcheraTestObj(List<String> ipList) {
            super(ipList);
        }
    }

    @Override
    public TestObj setupNewTestObj() throws Exception {
        List<String> ipList = new ArrayList<String>();
        ipList.add("10.0.0.1");
        ipList.add("127.0.0.1/8");
        ipList.add("245.0.0.1/32");
        return new IpAddressMatcheraTestObj(ipList);
    }
    
    @Test
    /** 
     * testIPRanges
     * @FeatureDomain                Tests
     * @FeatureKeywords              Test
     * @throws Exception             Exceptions possible
     */
    public void testIPRanges() throws Exception {
        IpAddressMatcheraTestObj testObj = (IpAddressMatcheraTestObj)setupNewTestObj();
        
        Assert.assertEquals(true, testObj.isInList("10.0.0.1"));
        Assert.assertEquals(false, testObj.isInList("10.0.1.1"));
        
        Assert.assertEquals(true, testObj.isInList("127.0.0.1"));
        Assert.assertEquals(true, testObj.isInList("127.1.0.0"));
        
        Assert.assertEquals(true, testObj.isInList("245.0.0.1"));
        Assert.assertEquals(false, testObj.isInList("245.1.0.1"));
        
        Assert.assertEquals(false, testObj.isInList("234.123.123.123"));
    }
}
