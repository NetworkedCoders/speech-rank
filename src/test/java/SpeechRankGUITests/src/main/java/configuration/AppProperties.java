package configuration;

public class AppProperties {

    public static String getAllUrl() {
        return ConfigurationProperties.getProperties().getProperty("app.url");
    }

}