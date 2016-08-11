package red.sukun1899;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SchemaSpyConfigMapTest {

  private static class TestConfig implements SchemaSpyConfig {
    private String databaseType;
    private String outputDirectory;
    private String host;
    private String dbName;
    private String user;
    private String password;
    private String charset;

    TestConfig() {
      databaseType = "mysql";
      outputDirectory = "target";
      host = "localhost";
      dbName = "sample";
      user = "root";
      password = "hogehoge";
      charset = "utf-8";
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
    public String getPassword() {
      return password;
    }

    @Override
    public String getCharset() {
      return charset;
    }

    @Override
    public String getOutputDirectory() {
      return outputDirectory;
    }

    public void setDatabaseType(String databaseType) {
      this.databaseType = databaseType;
    }

    public void setOutputDirectory(String outputDirectory) {
      this.outputDirectory = outputDirectory;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public void setDbName(String dbName) {
      this.dbName = dbName;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public void setCharset(String charset) {
      this.charset = charset;
    }
  }

  private TestConfig testConfig;

  @Before
  public void createConfig() {
    testConfig = new TestConfig();
  }

  @Test
  public void mapToSchemaSpyArguments() {
    // Arrange
    List<String> expected = new ArrayList<>();
    expected.add("-t");
    expected.add("mysql");
    expected.add("-host");
    expected.add("localhost");
    expected.add("-db");
    expected.add("sample");
    expected.add("-u");
    expected.add("root");
    expected.add("-p");
    expected.add("hogehoge");
    expected.add("-charset");
    expected.add("utf-8");
    expected.add("-o");
    expected.add("target/schemaspy");

    // Act
    SchemaSpyConfigMap schemaSpyConfigMap = new SchemaSpyConfigMap(testConfig);

    // Assert
    assertThat(schemaSpyConfigMap.toArgumentStrings(), is(expected));
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsExceptionWhenLackOfRequiredParameterValue() {
    // Arrange
    testConfig.setUser(null);

    // Act
    SchemaSpyConfigMap schemaSpyConfigMap = new SchemaSpyConfigMap(testConfig);
  }
}