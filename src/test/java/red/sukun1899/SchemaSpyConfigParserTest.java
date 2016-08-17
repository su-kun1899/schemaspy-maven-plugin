package red.sukun1899;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SchemaSpyConfigParserTest {

  @Test
  public void parseConfig() {
    // Arrange
    SchemaSpyConfig testConfig = () -> {
      Map<ParameterType, String> configrations = new LinkedHashMap<>();
      configrations.put(ParameterType.DATABASE_TYPE, "mysql");
      configrations.put(ParameterType.HOST, "localhost");
      configrations.put(ParameterType.DB_NAME, "test");
      configrations.put(ParameterType.USER, "root");
      configrations.put(ParameterType.PASSWORD, "hogehoge");
      configrations.put(ParameterType.CHARSET, "utf-8");
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
    configList.add("-p");
    configList.add("hogehoge");
    configList.add("-charset");
    configList.add("utf-8");
    configList.add("-o");
    configList.add("target/schemaspy");
    String[] expected = configList.toArray(new String[configList.size()]);

    // Act
    String[] actual = new SchemaSpyConfigParser(testConfig).parse();

    // Assert
    assertThat(actual, is(expected));
  }
}