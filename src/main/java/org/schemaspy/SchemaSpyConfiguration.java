/*
 * Copyright (C) 2017 Thomas Traude
 * Copyright (C) 2017 Daniel Watt
 * Copyright (C) 2018 Nils Petzaell
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
package org.schemaspy;

import org.schemaspy.cli.*;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Thomas Traude
 * @author Daniel Watt
 * @author Nils Petzaell
 */
public class SchemaSpyConfiguration {

    private ConfigFileArgumentParser configFileArgumentParser;

    private PropertyFileDefaultProviderFactory factory;

    // CB - mavenization
    public SchemaSpyConfiguration()
    {
        this.configFileArgumentParser = new ConfigFileArgumentParser();
        this.factory = new PropertyFileDefaultProviderFactory();
    }

    public CommandLineArgumentParser commandLineArgumentParser(String[] args, CommandLineArguments commandLineArguments) {
        Objects.requireNonNull(args);
        Optional<PropertyFileDefaultProvider> propertyFileDefaultProvider = findDefaultProvider(args);
        return new CommandLineArgumentParser(commandLineArguments, propertyFileDefaultProvider.orElse(null));
    }

    private Optional<PropertyFileDefaultProvider> findDefaultProvider(String... args) {
        Optional<String> configFileName = configFileArgumentParser.parseConfigFileArgumentValue(args);
        return factory.create(configFileName.orElse(null));
    }

}
