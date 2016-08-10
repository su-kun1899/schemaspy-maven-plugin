package com.higedrum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SchemaSpyConfigMapTest {

  @Test
  public void mapToSchemaSpyArguments() {
    // Arrange
    SchemaSpyConfig testConfig = new SchemaSpyConfig() {
      @Override
      public String getDatabaseType() {
        return "mysql";
      }

      @Override
      public String getHost() {
        return "localhost";
      }

      @Override
      public String getDbName() {
        return "sample";
      }

      @Override
      public String getUser() {
        return "root";
      }

      @Override
      public String getCharset() {
        return "utf-8";
      }
    };
    List<String> expected = new ArrayList<>();
    expected.add("-t");
    expected.add("mysql");
    expected.add("-host");
    expected.add("localhost");
    expected.add("-db");
    expected.add("sample");
    expected.add("-u");
    expected.add("root");
    expected.add("-charset");
    expected.add("utf-8");

    // Act
    SchemaSpyConfigMap schemaSpyConfigMap = new SchemaSpyConfigMap(testConfig);

    // Assert
    assertThat(schemaSpyConfigMap.toArgumentStrings(), is(expected));
  }
}