package com.higedrum;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * SchemaSpyの設定用Interface
 *
 * Created by su-kun1899 on 2016/08/10.
 */
public interface SchemaSpyConfig {

  String getDatabaseType();

  String getHost();

  String getDbName();

  String getUser();

}
