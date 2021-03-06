/** 
 * software for projectmanagement and documentation
 * 
 * @FeatureDomain                Collaboration 
 * @author                       Michael Schreiner <michael.schreiner@your-it-fellow.de>
 * @category                     collaboration
 * @copyright                    Copyright (c) 2014, Michael Schreiner
 * @license                      http://mozilla.org/MPL/2.0/ Mozilla Public License 2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package de.yaio.commons.config;

import de.yaio.commons.io.IOExceptionWithCause;
import de.yaio.commons.cli.CmdLineHelper;
import de.yaio.commons.io.IOUtils;

import java.util.Properties;


public abstract class ConfigurationHelper {
    protected ConfigurationHelper() {
    }

    public Configuration initConfiguration() {
        return initConfiguration(CmdLineHelper.getInstance());
    }

    public Configuration initConfiguration(final CmdLineHelper cmdLineHelper) {
        String configFile = cmdLineHelper.getConfigFile();
        try {
            return initConfiguration(cmdLineHelper, configFile);
        } catch (IOExceptionWithCause ex) {
            throw new IllegalArgumentException("cant read propertyFile for configOption", ex);
        }
    }

    public Configuration initConfiguration(final CmdLineHelper cmdLineHelper, final String configFile)
        throws IOExceptionWithCause {
        Properties props = IOUtils.getInstance().readProperties(configFile);
        return initConfiguration(cmdLineHelper, props);
    }

    public Configuration initConfiguration(final CmdLineHelper cmdLineHelper, final Properties props) {
        Configuration localConfiguration = getConfigurationInstance();
        localConfiguration.putArgs(cmdLineHelper.getCommandLine().getArgs());
        localConfiguration.putCliOptions(cmdLineHelper.getCommandLine().getOptions());
        localConfiguration.putProperties(props);
        initCalcedProperties(cmdLineHelper);

        return localConfiguration;
    }

    public abstract Configuration getConfigurationInstance();

    protected void initCalcedProperties(final CmdLineHelper cmdLineHelper) {
    }
}
