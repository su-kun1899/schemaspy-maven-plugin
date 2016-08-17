package red.sukun1899;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.R;

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
    return config.getConfigrations().entrySet().stream()
        .map(e -> {
          if (e.getKey().equals(ParameterType.OUTPUT_DIRECTORY)){
            String value = e.getValue();
            value = value == null ? "target" : value;
            value += "/schemaspy";
            e.setValue(value);
          }
          return e;
        })
        .map(e -> {
          if (isLackOfRequiredValue(e.getKey(), e.getValue())) {
            throw new IllegalArgumentException(e.getKey() + " is required. But value is empty.");
          }
          return e;
        })
        .filter(e -> e.getValue() != null)
        .collect(ArrayList::new,
            (list, entry) -> {
              list.add(entry.getKey().getParameter());
              list.add(entry.getValue());
            }, ArrayList::addAll);

  }

  private boolean isLackOfRequiredValue(final ParameterType key, final String value) {
    return key.isRequired() && (value == null || value.isEmpty());
  }

  SchemaSpyConfig getConfig() {
    return config;
  }
}
