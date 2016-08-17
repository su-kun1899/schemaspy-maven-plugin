package red.sukun1899;

import java.util.AbstractMap;
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
    validate(getConfig());
    List<String> configs = toList(getConfig());
    return toList(getConfig()).toArray(new String[configs.size()]);
  }

  private void validate(final SchemaSpyConfig config) {
    config.getConfigrations().entrySet().stream()
        .filter(entry -> entry.getKey().isRequired())
        .filter(entry -> entry.getValue() == null)
        .filter(entry -> entry.getKey().getDefaultValue() == null)
        .findFirst()
        .ifPresent(entry -> {
          throw new IllegalArgumentException(entry.getKey() + " is " +
              "required. " +
              "But value is empty.");
        });
  }

  private List<String> toList(final SchemaSpyConfig config) {
    return config.getConfigrations().entrySet().stream()
        .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), (entry.getValue() == null ?
            entry.getKey().getDefaultValue() : entry.getValue())))
        .filter(e -> e.getValue() != null)
        .collect(ArrayList::new,
            (list, entry) -> {
              list.add(entry.getKey().getParameter());
              list.add(entry.getValue());
            }, ArrayList::addAll);

  }

  public SchemaSpyConfig getConfig() {
    return config;
  }
}
