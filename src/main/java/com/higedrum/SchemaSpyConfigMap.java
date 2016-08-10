package com.higedrum;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * SchemaSpyに渡すパラメータ値を管理するMap
 *
 * Created by su-kun1899 on 2016/08/08.
 */
class SchemaSpyConfigMap {

  private Map<ParameterType, String> configMap;

  SchemaSpyConfigMap(final SchemaSpyConfig config) {
    configMap = new LinkedHashMap<>();
    configMap.put(ParameterType.DATABASE_TYPE, config.getDatabaseType());
    configMap.put(ParameterType.HOST, config.getHost());
    configMap.put(ParameterType.DB_NAME, config.getDbName());
    configMap.put(ParameterType.USER, config.getUser());
    configMap.put(ParameterType.CHARSET, config.getCharset());
  }

  public String put(final ParameterType key, final String value) {
    return configMap.put(key, value);
  }

  public String get(final ParameterType key) {
    return configMap.get(key);
  }

  List<String> toArgumentStrings() {
    List<String> argumentStrings = new ArrayList<>();
    configMap.forEach((key, value) -> {
      argumentStrings.add(key.getParameter());
      if (value != null && !value.isEmpty()){
        argumentStrings.add(value);
      }
    });

    return argumentStrings;
  }
}
