package com.higedrum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SchemaSpyConfigMapTest {

  @Test
  public void mapToSchemaSpyArguments() {
    List<String> expected = new ArrayList<>();
    expected.add("-t");
    expected.add("mysql");
    expected.add("-o");
    expected.add("target");
    expected.add("-host");
    expected.add("localhost");
    expected.add("-db");
    expected.add("sample");
    expected.add("-u");
    expected.add("root");
    expected.add("-charset");
    expected.add("utf-8");

    SchemaSpyConfigMap schemaSpyConfigMap = new SchemaSpyConfigMap();

    assertThat(schemaSpyConfigMap.toArgumentStrings(), is(expected));
  }
}