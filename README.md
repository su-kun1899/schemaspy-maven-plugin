# schemaspy-maven-plugin

[SchemaSpy](http://schemaspy.sourceforge.net/) を実行するためのMaven Pluginです。
SchemaSpyはDatabaseのMetaデータをグラフィカルなHTMLに出力し、データベースの構造を可視化します。

Maven Pluginとして実行可能にすることで、Maven Project との親和性を高めることを目的としています。

```
mvn -e com.higedrum:schemaspy-maven-plugin:schemaspy
```

# Release

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