package red.sukun1899;

import org.schemaspy.Main;

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

    Main.main(argv);
  }

  public SchemaSpyConfig getConfig() {
    return config;
  }
}
