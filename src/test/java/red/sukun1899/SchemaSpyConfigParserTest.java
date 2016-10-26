package red.sukun1899;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SchemaSpyConfigParserTest {

  private TestConfig testConfig;

  private static class TestConfig implements SchemaSpyConfig {
    private Map<ParameterType, String> configMap;

    TestConfig() {
      configMap = new LinkedHashMap<>();
      configMap.put(ParameterType.DATABASE_TYPE, "mysql");
      configMap.put(ParameterType.HOST, "localhost");
      configMap.put(ParameterType.PORT, "13306");
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

  @Before
  public void createConfig() {
    testConfig = new TestConfig();
  }

  @Test
  public void parseConfig() {
    // Arrange
    List<String> configList = new ArrayList<>();
    configList.add("-t");
    configList.add("mysql");
    configList.add("-host");
    configList.add("localhost");
    configList.add("-P");
    configList.add("13306");
    configList.add("-db");
    configList.add("test");
    configList.add("-u");
    configList.add("root");
    configList.add("-p");
    configList.add("hogehoge");
    configList.add("-charset");
    configList.add("utf-8");
    configList.add("-o");
    configList.add("target");
    String[] expected = configList.toArray(new String[configList.size()]);

    // Act
    String[] actual = new SchemaSpyConfigParser(testConfig).parse();

    // Assert
    assertThat(actual, is(expected));
  }

  @Test
  public void parseMinimumConfig() {
    // Arrange
    SchemaSpyConfig minimumConfig = () -> {
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

    List<String> configList = new ArrayList<>();
    configList.add("-t");
    configList.add("mysql");
    configList.add("-host");
    configList.add("localhost");
    configList.add("-db");
    configList.add("test");
    configList.add("-u");
    configList.add("root");
    configList.add("-o");
    configList.add("target/schemaspy");
    String[] expected = configList.toArray(new String[configList.size()]);

    // Act
    String[] actual = new SchemaSpyConfigParser(minimumConfig).parse();

    // Assert
    assertThat(actual, is(expected));
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsExceptionWhenLackOfRequiredParameterValue() {
    // Arrange
    testConfig.put(ParameterType.USER, null);

    // Act
    new SchemaSpyConfigParser(testConfig).parse();
  }

  @Test
  public void ignoreEmptyOptionalParameter() {
    // Arrange
    testConfig.put(ParameterType.CHARSET, null);

    // Act
    String[] actual = new SchemaSpyConfigParser(testConfig).parse();

    // Assert
    assertThat(Arrays.asList(actual).contains(ParameterType.CHARSET.getParameter()), is(false));
  }

  @Test
  public void defaultOutputDirectory() {
    // Arrange
    testConfig.put(ParameterType.OUTPUT_DIRECTORY, null);

    // Act
    String[] actual = new SchemaSpyConfigParser(testConfig).parse();

    // Assert
    int outputDirectoryParamIndex = Arrays.asList(actual).indexOf(ParameterType.OUTPUT_DIRECTORY.getParameter()
    );
    int outputDirectoryValueIndex = outputDirectoryParamIndex + 1;
    assertThat(actual[outputDirectoryValueIndex], is("target/schemaspy"));
  }
}