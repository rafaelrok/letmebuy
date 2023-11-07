package com.rafaelvieira.letmebuy.utils;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author rafae
 */

@MappedSuperclass
public class EntityWithUUID {
    @Id
    // @Type(value = "uuid-char")
    private UUID id;

    public EntityWithUUID() {
        this.id = UUID.randomUUID();
    }
}
