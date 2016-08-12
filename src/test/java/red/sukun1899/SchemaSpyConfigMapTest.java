package red.sukun1899;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SchemaSpyConfigMapTest {

  private static class TestConfig implements SchemaSpyConfig {
    private Map<ParameterType, String> configMap;

    TestConfig() {
      configMap = new LinkedHashMap<>();
      configMap.put(ParameterType.DATABASE_TYPE, "mysql");
      configMap.put(ParameterType.HOST, "localhost");
      configMap.put(ParameterType.DB_NAME, "test");
      configMap.put(ParameterType.USER, "root");
      configMap.put(ParameterType.PASSWORD, "hogehoge");
      configMap.put(ParameterType.CHARSET, "utf-8");
      configMap.put(ParameterType.OUTPUT_DIRECTORY, "target");
    }

    @Override
    public Map<ParameterType, String> getConfigrations() {
      return configMap;
    }

    String put(final ParameterType key, final String value) {
      return configMap.put(key, value);
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
    expected.add("test");
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
    testConfig.put(ParameterType.USER, null);

    // Act
    SchemaSpyConfigMap schemaSpyConfigMap = new SchemaSpyConfigMap(testConfig);
  }

  @Test
  public void ignoreEmptyOptionalParameter() {
    // Arrange
    testConfig.put(ParameterType.CHARSET, null);

    // Act
    SchemaSpyConfigMap schemaSpyConfigMap = new SchemaSpyConfigMap(testConfig);

    // Assert
    assertThat(schemaSpyConfigMap.get(ParameterType.CHARSET), nullValue());
  }

  @Test
  public void defaultOutputDirectory() {
    // Arrange
    testConfig.put(ParameterType.OUTPUT_DIRECTORY, null);

    // Act
    SchemaSpyConfigMap schemaSpyConfigMap = new SchemaSpyConfigMap(testConfig);

    // Assert
    assertThat(schemaSpyConfigMap.get(ParameterType.OUTPUT_DIRECTORY), is("target/schemaspy"));
  }

  @Test
  public void mapToMinimuSchemaSpyArguments() {
    // Arrange
    SchemaSpyConfig config = () -> {
      Map<ParameterType, String> configrations = new LinkedHashMap<>();
      configrations.put(ParameterType.DATABASE_TYPE, "mysql");
      configrations.put(ParameterType.HOST, "localhost");
      configrations.put(ParameterType.DB_NAME, "test");
      configrations.put(ParameterType.USER, "root");
      configrations.put(ParameterType.PASSWORD, null);
      configrations.put(ParameterType.CHARSET, null);
      configrations.put(ParameterType.OUTPUT_DIRECTORY, null);

      return configrations;
    };

    List<String> expected = new ArrayList<>();
    expected.add("-t");
    expected.add("mysql");
    expected.add("-host");
    expected.add("localhost");
    expected.add("-db");
    expected.add("test");
    expected.add("-u");
    expected.add("root");
    expected.add("-o");
    expected.add("target/schemaspy");

    // Act
    SchemaSpyConfigMap schemaSpyConfigMap = new SchemaSpyConfigMap(config);

    // Assert
    assertThat(schemaSpyConfigMap.toArgumentStrings(), is(expected));
  }
}