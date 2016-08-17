package red.sukun1899;

import java.util.ArrayList;
import java.util.List;

/**
 * SchemaSpyに渡すパラメータ用のParser
 *
 * Created by su-kun1899 on 2016/08/17.
 */
class SchemaSpyConfigParser {

  private SchemaSpyConfig config;

  SchemaSpyConfigParser(SchemaSpyConfig config) {
    this.config = config;
  }

  String[] parse() {
    List<String> configs = toList(getConfig());
    return configs.toArray(new String[configs.size()]);
  }

  private List<String> toList(final SchemaSpyConfig config) {
    List<String> configs = new ArrayList<>();
    config.getConfigrations().forEach((key, value) -> {
      switch (key) {
        case OUTPUT_DIRECTORY:
          value = value == null ? "target" : value;
          value += "/schemaspy";
          break;
        default:
          break;
      }

      if (isLackOfRequiredValue(key, value)) {
        throw new IllegalArgumentException(key + " is required. But value is empty.");
      }

      configs.add(key.getParameter());
      if (value != null && !value.isEmpty()) {
        configs.add(value);
      }
    });

    return configs;
  }

  private boolean isLackOfRequiredValue(final ParameterType key, final String value) {
    return key.isRequired() && (value == null || value.isEmpty());
  }

  SchemaSpyConfig getConfig() {
    return config;
  }
}
