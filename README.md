# schemaspy-maven-plugin

schemaspy-maven-plugin is maven plugin for [SchemaSpy](http://schemaspy.sourceforge.net/).  

SchemaSpy is graphical database schema metadata browser.
It is a Java-based tool that analyzes the metadata of a schema in a database, 
and generates a visual representation of it in html format.

This plugin may help some maven projects and developers.

Metadata are...

- Tables
- Columns
- Indexes
- Constraints
- RelationShips
- etc

## Requirement

schemaspy-maven-plugin requires... 

- java 1.8.x
- maven 3.3.x

## Usage

### pom.xml setting

```xml
<project ...>
    ...
      <build>
        <plugins>
          <plugin>
            <groupId>red.sukun1899</groupId>
            <artifactId>schemaspy-maven-plugin</artifactId>
            <version>1.1.0</version>
            <configuration>
              <databaseType>mysql</databaseType>
              <host>yourDatabaseHost</host>
              <user>yourDatabaseUser</user>
              <password>yourDatabasePassword</password>
              <dbName>yourDatabaseName</dbName>
              <outputDirectory>pathForGeneretedHtml</outputDirectory>
            </configuration>
          </plugin>
        </plugins>
      </build>
</project>
```

### Generate Html

```
mvn red.sukun1899:schemaspy-maven-plugin:schemaspy
```

### Sample Project

see [schemaspy-plugin-sample](https://github.com/su-kun1899/schemaspy-plugin-sample)

### Caution

- Currently Supported Only MySQL

# Release

This is the item for project owners.   
It is how to deploy to maven central repository.

```
git tag -a vX.X.X
mvn clean deploy -DperformRelease=true -s settings.xml -P release
git push origin vX.X.X
```

- Deploy snapshot artifacts into repository
  - https://oss.sonatype.org/content/repositories/snapshots
- Deploy release artifacts into the staging repository
  - https://oss.sonatype.org/service/local/staging/deploy/maven2
- Promote staged artifacts into repository 'Releases'
- Download snapshot and release artifacts from group 
  - https://oss.sonatype.org/content/groups/public
- Download snapshot, release and staged artifacts from staging group  
  - https://oss.sonatype.org/content/groups/staging