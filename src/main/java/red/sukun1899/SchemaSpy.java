package red.sukun1899;

import net.sourceforge.schemaspy.Config;
import net.sourceforge.schemaspy.SchemaAnalyzer;

/**
 * SchemaSpyのエントリポイントとなるクラス
 *
 * pomから受け取ったConfigurationをSchemaSpyに連携して実行します
 *
 * Created by su-kun1899 on 2016/08/08.
 */
public class SchemaSpy {

  private SchemaSpyConfig config;

  public SchemaSpy(final SchemaSpyConfig config) {
    this.config = config;
  }

  void execute() throws Exception {
    String[] argv = new SchemaSpyConfigParser(getConfig()).parse();

    SchemaAnalyzer analyzer = new SchemaAnalyzer();
    analyzer.analyze(new Config(argv));
  }

  public SchemaSpyConfig getConfig() {
    return config;
  }

  public void setConfig(SchemaSpyConfig config) {
    this.config = config;
  }
}
