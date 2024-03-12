package vn.fpt.models.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Attributes {

    private List<String> baseUrl;
    private List<String> displayName;
    private List<String> domain;
    private List<String> imgPath;
    private List<String> description;
    private List<String> requestAccess;
    private List<String> version;
}
