package com.higedrum;

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class SchemaSpyMojoTest {

  @Rule
  public MojoRule mojoRule = new MojoRule();

  @Rule
  public TestResources resources = new TestResources();

  @Test
  public void customSettings() throws Exception {
    // src/test/projects/project/pom.xml に書かれた設定を元にMojoインスタンスを作成
    File baseDir = resources.getBasedir("project");
    File pom = new File(baseDir, "pom.xml");

    // 'schemaspy' ゴールを実行
    Mojo mojo = mojoRule.lookupMojo("schemaspy", pom);
    mojo.execute();

    assertTrue(true);
  }

  @Test
  public void mavenGoalIsSchemaSpy() throws Exception {
    // Arrange
    final String goal = "schemaspy";
    File baseDir = resources.getBasedir("project");
    File pom = new File(baseDir, "pom.xml");

    // Act
    Mojo mojo = mojoRule.lookupMojo(goal, pom);

    // Assert
    assertTrue(mojo instanceof SchemaSpyMojo);
  }
}