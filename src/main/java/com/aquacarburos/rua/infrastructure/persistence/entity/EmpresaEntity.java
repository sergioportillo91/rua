package com.aquacarburos.rua.infrastructure.persistence.entity;

import com.aquacarburos.rua.domain.model.RolNombre;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "empresa", schema = "schema_rua")
@Data
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nit", nullable = false)
    private String nit;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolNombre rol;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "pago", nullable = false)
    private boolean pago;

    @Column(name = "fecha_creacion",nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion",nullable = false)
    private LocalDateTime fechaModificacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDateTime.now();
    }
}