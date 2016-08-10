package com.higedrum;

import net.sourceforge.schemaspy.Config;
import net.sourceforge.schemaspy.SchemaAnalyzer;

import java.util.List;

/**
 * SchemaSpyのエントリポイントとなるクラス
 *
 * pomから受け取ったConfigurationをSchemaSpyに連携して実行します
 *
 * Created by su-kun1899 on 2016/08/08.
 */
public class SchemaSpy {

  private SchemaSpyConfigMap configMap;

  public SchemaSpy(SchemaSpyConfigMap configMap) {
    this.configMap = configMap;
  }

  void execute() throws Exception {

    List<String> stringArgs = configMap.toArgumentStrings();
    String[] argv = stringArgs.toArray(new String[stringArgs.size()]);

    SchemaAnalyzer analyzer = new SchemaAnalyzer();
    analyzer.analyze(new Config(argv));

  }
}
