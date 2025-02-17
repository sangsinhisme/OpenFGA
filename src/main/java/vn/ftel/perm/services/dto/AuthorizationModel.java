package vn.ftel.perm.services.dto;

import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.Serializable;

@Getter
public abstract class AuthorizationModel implements Serializable {
    @ConfigProperty(name = "AUTHORIZATION_MODEL_ID")
    String authorizationModelId;

}
