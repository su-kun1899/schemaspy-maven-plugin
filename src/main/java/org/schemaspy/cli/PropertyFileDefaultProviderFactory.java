/*
 * Copyright (C) 2017 Thomas Traude
 * Copyright (C) 2017 Daniel Watt
 *
 * This file is part of SchemaSpy.
 *
 * SchemaSpy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SchemaSpy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SchemaSpy. If not, see <http://www.gnu.org/licenses/>.
 */
package org.schemaspy.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

/**
 * This class creates instances of {@link PropertyFileDefaultProvider} based on a name of the {@link java.util.Properties} file.
 * @author Thomas Traude
 * @author Daniel Watt
 */
public class PropertyFileDefaultProviderFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String DEFAULT_PROPERTIES_FILE_NAME = "schemaspy.properties";

    private static boolean exists(String propertiesFilename) {
        return new File(propertiesFilename).exists();
    }

    /**
     * Return a {@link PropertyFileDefaultProvider} instance based on propertiesFilename.
     * <p>
     * If for the given propertiesFilename there exists no file the method will exit the application.
     * <p>
     * If the given propertiesFilename is null it falls back to the {@link #DEFAULT_PROPERTIES_FILE_NAME}.
     * If this file does not exist the method returns {@link Optional#empty()}.
     *
     * @param propertiesFilename
     * @return PropertyFileDefaultProvider instance of the propertiesfileName, empty if no such file exists.
     */
    public Optional<PropertyFileDefaultProvider> create(String propertiesFilename) {
        if (propertiesFilename != null) {
            if (exists(propertiesFilename)) {
				LOGGER.info("Found configuration file: {}", propertiesFilename);
                PropertyFileDefaultProvider value = new PropertyFileDefaultProvider(propertiesFilename);
                return Optional.of(value);
            } else {
				LOGGER.error("Could not find config file: {}", propertiesFilename);
                System.exit(0);
                return null;
            }
        }

        if (exists(DEFAULT_PROPERTIES_FILE_NAME)) {
            LOGGER.info("Found configuration file: {}",DEFAULT_PROPERTIES_FILE_NAME);
            return Optional.of(new PropertyFileDefaultProvider(DEFAULT_PROPERTIES_FILE_NAME));
        }
        return Optional.empty();
    }

}
