package com.higedrum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by koji-sudo on 2016/08/08.
 */
public class SchemaSpyConfigMap {

  private Map<ParameterType, String> configMap;

  public SchemaSpyConfigMap() {
    configMap =  new LinkedHashMap<>();
    configMap.put(ParameterType.DATABASE_TYPE, "mysql");
    configMap.put(ParameterType.OUTPUT_DIRECTORY, "target");
    configMap.put(ParameterType.HOST, "localhost");
    configMap.put(ParameterType.DB_NAME, "sample");
    configMap.put(ParameterType.USER, "root");
    configMap.put(ParameterType.CHAR_SET, "utf-8");
  }

  public String put(ParameterType key, String value){
    return configMap.put(key, value);
  }

  public List<String> toArgumentStrings(){
    List<String> argumentStrings = new ArrayList<>();
    configMap.forEach((key, value)->{
      argumentStrings.add(key.getParameter());
      argumentStrings.add(value);
    });

    return argumentStrings;
  }
}
