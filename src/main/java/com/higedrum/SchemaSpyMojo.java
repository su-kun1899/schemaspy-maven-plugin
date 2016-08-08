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
   * Location of the file.
   */
  @Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
  private File outputDirectory;

  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("sample plugin start!");
    //getLog().info("project.build.directory is " + outputDirectory.getAbsolutePath());
  }
}
