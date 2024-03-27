package vn.fpt.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.MessageInterpolator;
import jakarta.ws.rs.container.ContainerRequestContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j
@RequestScoped
public class AppMessageInterpolator implements MessageInterpolator {

    @jakarta.ws.rs.core.Context
    ContainerRequestContext requestContext;

    @Override
    public String interpolate(String messageTemplate, Context context) {
        // Get user's locale from request header
        Locale locale = requestContext.getLanguage();
        return this.interpolate(messageTemplate, context, locale);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {

        return messageTemplate;
    }
}
