package com.tp.facturacion.base;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


// ==========================================
// Superclases / Herencia
// ==========================================


@MappedSuperclass
public abstract class EntityId {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }
}