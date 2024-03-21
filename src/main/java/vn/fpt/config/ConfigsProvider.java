package vn.fpt.config;

import org.eclipse.microprofile.config.ConfigProvider;

public class ConfigsProvider {

    public static final String APP_ID = ConfigProvider.getConfig().getValue("application.id", String.class);
    public static final String DATABASE_SCHEMA = ConfigProvider.getConfig().getValue("quarkus.hibernate-orm.database.default-schema", String.class);
}
