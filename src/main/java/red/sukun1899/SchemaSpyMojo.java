package red.sukun1899;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PluginのエントリポイントとなるMojo
 *
 * Created by su-kun1899 on 2016/08/08.
 */
@Mojo(name = "schemaspy", defaultPhase = LifecyclePhase.SITE)
class SchemaSpyMojo extends AbstractMojo implements SchemaSpyConfig {
  /**
   * Type of database.
   */
  @Parameter(defaultValue = "mysql", property = "databaseType", required = true)
  private String databaseType;

  /**
   * Location of the file.
   */
  @Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
  private String outputDirectory;

  /**
   * Host of database.
   */
  @Parameter(defaultValue = "localhost", property = "host", required = true)
  private String host;

  /**
   * Port number of database.
   */
  @Parameter(defaultValue = "3306", property = "port", required = false)
  private String port;

  /**
   * Name of database to connect to
   */
  @Parameter(defaultValue = "sample", property = "dbName", required = true)
  private String dbName;

  /**
   * Valid database user id with read access.
   */
  @Parameter(defaultValue = "root", property = "user", required = true)
  private String user;

  /**
   * Password associated with that user.
   */
  @Parameter(property = "password")
  private String password;

  /**
   * Charset
   */
  @Parameter(defaultValue = "utf-8", property = "charset", required = true)
  private String charset;

  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("schemaspy-maven-plugin start!");

    SchemaSpy schemaSpy = new SchemaSpy(this);
    try {
      schemaSpy.execute();
    } catch (Exception e) {
      throw new MojoFailureException("Failed schemaSpy execute", e);
    }

    getLog().info("schemaspy-maven-plugin end!");
  }

  @Override
  public Map<ParameterType, String> getConfigrations() {
    Map<ParameterType, String> configrations = new LinkedHashMap<>();
    configrations.put(ParameterType.DATABASE_TYPE, getDatabaseType());
    configrations.put(ParameterType.HOST, getHost());
    configrations.put(ParameterType.PORT, getPort());
    configrations.put(ParameterType.DB_NAME, getDbName());
    configrations.put(ParameterType.USER, getUser());
    configrations.put(ParameterType.PASSWORD, getPassword());
    configrations.put(ParameterType.CHARSET, getCharset());
    configrations.put(ParameterType.OUTPUT_DIRECTORY, getOutputDirectory());

    return configrations;
  }

  public String getDatabaseType() {
    return databaseType;
  }

  public String getOutputDirectory() {
    return outputDirectory;
  }

  public String getHost() {
    return host;
  }

  public String getDbName() {
    return dbName;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public String getCharset() {
    return charset;
  }

  public String getPort() {
    return port;
  }
}
