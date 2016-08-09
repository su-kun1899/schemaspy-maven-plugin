package com.higedrum;

/**
 * Created by koji-sudo on 2016/08/08.
 */
public enum ParameterType {
  DATABASE_TYPE("-t"),
  OUTPUT_DIRECTORY("-o"),
  HOST("-host"),
  DB_NAME("-db"),
  USER("-u"),
  CHAR_SET("-charset");

  ParameterType(String parameter) {
    this.parameter = parameter;
  }

  private String parameter;

  public String getParameter() {
    return parameter;
  }
}
