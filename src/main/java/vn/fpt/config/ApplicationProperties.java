package vn.fpt.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "application")
public interface ApplicationProperties {

    @WithName("message")
    String message();

}