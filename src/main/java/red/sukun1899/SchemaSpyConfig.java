package red.sukun1899;

import java.util.Map;

/**
 * SchemaSpyの設定用Interface
 *
 * Created by su-kun1899 on 2016/08/10.
 */
interface SchemaSpyConfig {
  Map<ParameterType, String> getConfigrations();
}