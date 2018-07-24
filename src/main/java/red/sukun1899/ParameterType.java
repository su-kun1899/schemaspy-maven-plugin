package red.sukun1899;

/**
 * shemaSpyに渡すパラメータを管理するEnum
 *
 * Created by su-kun1899 on 2016/08/08.
 */
enum ParameterType {
  DATABASE_TYPE("-t", false),
  OUTPUT_DIRECTORY("-o", true){
    @Override
    public String getDefaultValue(){
      return "target/schemaspy";
    }
  },
  HOST("-host", false),
  DB_NAME("-db", true),
  USER("-u", true),
  PASSWORD("-p", false),
  CHARSET("-charset", false),
  SCHEMA("-s", false);

  ParameterType(final String parameter, final boolean isRequired) {
    this.parameter = parameter;
    this.isRequired = isRequired;
  }

  private String parameter;

  public boolean isRequired() {
    return isRequired;
  }

  private boolean isRequired;

  public String getDefaultValue() {
    return null;
  }

  public String getParameter() {
    return parameter;
  }
}
