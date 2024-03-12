package vn.fpt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.fpt.models.audit.AuditListener;
import vn.fpt.secure.SecurityUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    private String createdBy;

    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    @JsonIgnore
    private Instant lastModifiedDate = Instant.now();

    @PrePersist
    public void onPrePersist() {
        audit(true);
    }

    @PreUpdate
    public void onPreUpdate() {
        audit(false);
    }

    @PreRemove
    public void onPreRemove() {
        audit(false);
    }

    private void audit(Boolean isNew) {
        if(Boolean.TRUE.equals(isNew)) {
            setCreatedBy(SecurityUtil.getCurrentAuditor().orElse(null));
            setLastModifiedBy(SecurityUtil.getCurrentAuditor().orElse(null));
            setCreatedDate(Instant.now());
            setLastModifiedDate(Instant.now());
        } else {
            setLastModifiedBy(SecurityUtil.getCurrentAuditor().orElse(null));
            setLastModifiedDate(Instant.now());
        }
    }
}
