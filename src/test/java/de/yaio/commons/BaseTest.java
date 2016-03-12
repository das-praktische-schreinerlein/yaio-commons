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
package de.yaio.commons;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** 
 * baseinterface for tests
 */
@RunWith(JUnit4.class)
public abstract class BaseTest {
    
    protected TestServices testService = new TestServices();
    
    /** 
     * testobject
     */
    public interface TestObj  {
        String toString();
    }

    /** 
     * setup the a TestObj for the test
     * @return                       the dataobj for the test
     * @throws Exception             io-Exceptions possible
     */
    public abstract TestObj setupNewTestObj() throws Exception;

    /** 
     * setup the test
     * @throws Exception             io-Exceptions possible
     */
    @Before
    public void setUp() throws Exception {
    };

    /** 
     * teardown the test
     * @throws Exception             io-Exceptions possible
     */
    @After
    public void tearDown() throws Exception {
    };
}
