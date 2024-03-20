package vn.fpt.config;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import io.quarkus.arc.DefaultBean;
import io.quarkus.datasource.runtime.DataSourceSupport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;

public class DataSourceProducer {

    @Inject
    DataSourceSupport dataSourceSupport;

    @Inject
    AgroalDataSource defaultDataSource;

    @Inject
    @DataSource("portal")
    AgroalDataSource portalDataSource;

    @Produces
    @Singleton
    @DefaultBean
    public AgroalDataSource dataSource() {
        if (dataSourceSupport.getInactiveNames().contains("portal")) {
            return portalDataSource;
        } else {
            return defaultDataSource;
        }
    }
}
