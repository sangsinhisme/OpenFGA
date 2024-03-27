package vn.fpt.repositories;

import io.quarkus.hibernate.orm.PersistenceUnit;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;

import vn.fpt.models.EntityDevice;

import java.util.UUID;

@Default
@ApplicationScoped
@PersistenceUnit("portal")
public class EntityDeviceRepository implements PanacheRepositoryBase<EntityDevice, UUID> {

    // put your custom logic here as instance methods

}
