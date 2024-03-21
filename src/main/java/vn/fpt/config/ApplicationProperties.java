package vn.fpt.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigMapping(prefix = "application")
public interface ApplicationProperties {

    @WithName("id")
    String appId();

}