package vn.fpt.models.audit;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import vn.fpt.models.AbstractAuditingEntity;

import java.time.Instant;

@Slf4j
@RequestScoped
public class AuditListener {

    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(String username) {
        currentUser.set(username);
    }

    public static String getCurrentUser() {
        try {
            return currentUser.get();
        } catch (Exception ex) {
            return "anonymous";
        }
    }

    public static void clearCurrentUser() {
        currentUser.remove();
    }

    @PrePersist
    private void auditPrePersist(AbstractAuditingEntity auditingEntity) {

        String username = currentUser.get();

        if(username != null) {

            auditingEntity.setCreatedBy(username);
            auditingEntity.setLastModifiedBy(username);
        }
        auditingEntity.setCreatedDate(Instant.now());
        auditingEntity.setLastModifiedDate(Instant.now());
    }

    @PreUpdate
    @PreRemove
    private void auditPreChange(AbstractAuditingEntity auditingEntity) {

        String username = currentUser.get();

        if(username != null) {

            auditingEntity.setLastModifiedBy(username);
        }
        auditingEntity.setLastModifiedDate(Instant.now());
    }

}
