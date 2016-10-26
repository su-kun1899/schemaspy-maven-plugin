package red.sukun1899;

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SchemaSpyMojoTest {

  @Rule
  public MojoRule mojoRule = new MojoRule();

  @Rule
  public TestResources resources = new TestResources();

  @Test
  public void customSettings() throws Exception {
    // Arrange
    File baseDir = resources.getBasedir("project");
    File pom = new File(baseDir, "pom.xml");
    String databaseType = "mysql";
    String host = "localhost";
    String port = "13306";
    String user = "test_user";
    String dbName = "test";
    String outputDirectory = "target";
    String password = "hogehoge";

    // Act
    SchemaSpyMojo mojo = (SchemaSpyMojo) mojoRule.lookupMojo("schemaspy", pom);

    // Assert
    assertThat(mojo.getDatabaseType(), is(databaseType));
    assertThat(mojo.getHost(), is(host));
    assertThat(mojo.getPort(), is(port));
    assertThat(mojo.getUser(), is(user));
    assertThat(mojo.getDbName(), is(dbName));
    assertThat(mojo.getOutputDirectory(), is(outputDirectory));
    assertThat(mojo.getPassword(), is(password));
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

  @Test
  public void generateHtml() throws Exception {
    // Arrange
    File baseDir = resources.getBasedir("project");
    File pom = new File(baseDir, "pom.xml");

    // Act
    Mojo mojo = mojoRule.lookupMojo("schemaspy", pom);
    mojo.execute();

    // Assert
    File file = new File("target/schemaspy/index.html");
    assertTrue(file.exists());
  }

  @Test
  public void generateHtmlWhenMinimumConfigration() throws Exception {
    // Arrange
    File baseDir = resources.getBasedir("minimum");
    File pom = new File(baseDir, "pom.xml");

    // Act
    Mojo mojo = mojoRule.lookupMojo("schemaspy", pom);
    mojo.execute();

    // Assert
    File file = new File("target/schemaspy/index.html");
    assertTrue(file.exists());
  }
}