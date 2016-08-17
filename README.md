# schemaspy-maven-plugin

[SchemaSpy](http://schemaspy.sourceforge.net/) を実行するためのMaven Pluginです。
SchemaSpyはDatabaseのMetaデータをグラフィカルなHTMLに出力し、データベースの構造を可視化します。

Maven Pluginとして実行可能にすることで、Maven Project との親和性を高めることを目的としています。

## Description

このプラグインは、任意のデータベースにアクセスし、メタ情報を取得します。
その情報を元に、グラフィカルなHTMLを出力します。
メタ情報とは下記のようなものを指します。

- Tables
- Columns
- Indexes
- Constraints
- RelationShips
- etc

## Requirement

### Java versions

schemaspy-maven-plugin requires java 1.8.x

### Maven versions

schemaspy-maven-plugin requires maven 3.3.x

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
            <version>1.0.2</version>
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

下記は管理者向けの手順です。
Maven Centralにdeployするための手順です。

```
mvn clean deploy -DperformRelease=true -s settings.xml -P release
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