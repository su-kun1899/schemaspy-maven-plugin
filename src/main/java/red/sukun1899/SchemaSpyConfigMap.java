package red.sukun1899;

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
    config.getConfigrations().forEach((key, value) -> {
      switch (key) {
        case OUTPUT_DIRECTORY:
          put(key, value + "/schemaspy");
          break;
        default:
          put(key, value);
          break;
      }
    });
  }

  public String put(final ParameterType key, final String value) {
    if (validateRequiredValue(key, value)) {
      throw new IllegalArgumentException(key + " is required. But value is empty.");
    }

    return configMap.put(key, value);
  }

  public String get(final ParameterType key) {
    return configMap.get(key);
  }

  List<String> toArgumentStrings() {
    List<String> argumentStrings = new ArrayList<>();
    configMap.forEach((key, value) -> {
      argumentStrings.add(key.getParameter());
      if (value != null && !value.isEmpty()) {
        argumentStrings.add(value);
      }
    });

    return argumentStrings;
  }

  private boolean validateRequiredValue(final ParameterType key, final String value) {
    return key.isRequired() && (value == null || value.isEmpty());
  }

  public Map<ParameterType, String> getConfigMap() {
    return configMap;
  }

  public void setConfigMap(Map<ParameterType, String> configMap) {
    this.configMap = configMap;
  }
}
