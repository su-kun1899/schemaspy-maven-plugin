package com.higedrum;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Created by koji-sudo on 2016/08/08.
 */
@Mojo(name = "schemaspy", defaultPhase = LifecyclePhase.SITE)
public class SchemaSpyMojo extends AbstractMojo {
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
  @Parameter(defaultValue = "test", property = "dbName", required = true)
  private String dbName;

  /**
   * Valid database user id with read access.
   */
  @Parameter(defaultValue = "root", property = "user", required = true)
  private String user;

  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("plugin start!");

    SchemaSpy schemaSpy = new SchemaSpy(new SchemaSpyConfigMap());
    try {
      schemaSpy.execute();
    } catch (Exception e) {
      throw new MojoFailureException("Failed schemaSpy execute", e);
    }

    getLog().info("plugin end!");
  }
}
