package com.higedrum;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * PluginのエントリポイントとなるMojo
 *
 * Created by su-kun1899 on 2016/08/08.
 */
@Mojo(name = "schemaspy", defaultPhase = LifecyclePhase.SITE)
public class SchemaSpyMojo extends AbstractMojo implements SchemaSpyConfig {
  /**
   * Type of database.
   */
  @Parameter(defaultValue = "mysql", property = "databaseType", required = true)
  private String databaseType;

  /**
   * Location of the file.
   */
  @Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
  private String outputDirectory;

  /**
   * Host of database.
   */
  @Parameter(defaultValue = "localhost", property = "host", required = true)
  private String host;

  /**
   * Name of database to connect to
   */
  @Parameter(defaultValue = "sample", property = "dbName", required = true)
  private String dbName;

  /**
   * Valid database user id with read access.
   */
  @Parameter(defaultValue = "root", property = "user", required = true)
  private String user;

  @Parameter(defaultValue = "utf-8", property = "charset", required = true)
  private String charset;

  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("schemaspy-maven-plugin start!");

    SchemaSpy schemaSpy = new SchemaSpy(new SchemaSpyConfigMap(this));
    try {
      schemaSpy.execute();
    } catch (Exception e) {
      throw new MojoFailureException("Failed schemaSpy execute", e);
    }

    getLog().info("schemaspy-maven-plugin end!");
  }

  @Override
  public String getDatabaseType() {
    return databaseType;
  }

  @Override
  public String getHost() {
    return host;
  }

  @Override
  public String getDbName() {
    return dbName;
  }

  @Override
  public String getUser() {
    return user;
  }

  @Override
  public String getCharset() {
    return charset;
  }
}
