package com.higedrum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by koji-sudo on 2016/08/08.
 */
public class SchemaSpyConfigMap {

  private Map<ParameterType, String> configMap = new HashMap<ParameterType, String>();

  public String put(ParameterType key, String value){
    return configMap.put(key, value);
  }

  public String toStringArgument(){
    // TODO MapをSchemaSpy用のArgsに変換する
    return null;
  }
}
