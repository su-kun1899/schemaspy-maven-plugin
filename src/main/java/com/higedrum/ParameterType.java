package com.higedrum;

/**
 * shemaSpyに渡すパラメータを管理するEnum
 *
 * Created by su-kun1899 on 2016/08/08.
 */
enum ParameterType {
  DATABASE_TYPE("-t"),
  OUTPUT_DIRECTORY("-o"),
  HOST("-host"),
  DB_NAME("-db"),
  USER("-u"),
  CHARSET("-charset");

  ParameterType(String parameter) {
    this.parameter = parameter;
  }

  private String parameter;

  public String getParameter() {
    return parameter;
  }
}
