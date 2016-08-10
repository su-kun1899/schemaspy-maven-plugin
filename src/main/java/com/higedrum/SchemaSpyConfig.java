package com.higedrum;

/**
 * SchemaSpyの設定用Interface
 *
 * Created by su-kun1899 on 2016/08/10.
 */
interface SchemaSpyConfig {

  String getDatabaseType();

  String getHost();

  String getDbName();

  String getUser();

  String getCharset();

  String getOutputDirectory();
}
