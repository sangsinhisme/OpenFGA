package vn.fpt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fpt.config.ApplicationConfiguration;
import vn.fpt.models.enumeration.ActionStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Cacheable
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "entity_device", schema = ApplicationConfiguration.PORTAL_SCHEMA)
public class EntityDevice extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Size(max = 500)
    @Column(name = "name", unique = true, length = 500)
    private String name;

    @Size(max = 2000)
    @Column(name = "description", length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActionStatus status;
    
    // return name as uppercase in the model
    public String getName() {
        return name.toUpperCase();
    }

    // store all names in lowercase in the DB
    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
