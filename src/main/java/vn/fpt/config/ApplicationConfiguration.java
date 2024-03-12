package vn.fpt.config;

import io.agroal.api.AgroalDataSource;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;

import io.quarkus.agroal.DataSource;
import io.quarkus.datasource.runtime.DataSourceSupport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

public class ApplicationConfiguration {

    public static final String PORTAL_SCHEMA = "camera_portal";

    @Produces
    @ApplicationScoped
    public MeterFilter enableHistogram() {
        return new MeterFilter() {
            @Override
            public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                if (id.getName().startsWith("http.server.requests")) {
                    return DistributionStatisticConfig.builder()
                            .percentiles(0.5, 0.95, 0.99, 0.999)
                            .build()
                            .merge(config);
                }
                return config;
            }
        };
    }

}