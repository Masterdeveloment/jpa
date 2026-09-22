package com.tp.facturacion.model;
import com.tp.facturacion.base.AuditoriaApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "punto_venta")
public class PuntoVenta extends AuditoriaApp {
    @Column(nullable = false)
    private int numero;
    @Column (nullable = false)
    private String descripcion;
    @Column (nullable = false)
    private String tipoEmision;
    @Column (nullable = false)
    private String domicilioComercial;


    //Metodo constructor vacio
    public PuntoVenta() {
    }

    //metodo constructor con parametros
    public PuntoVenta(int numero, String descripcion, String tipoEmision, String domicilioComercial) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.tipoEmision = tipoEmision;
        this.domicilioComercial = domicilioComercial;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoEmision() {
        return tipoEmision;
    }

    public void setTipoEmision(String tipoEmision) {
        this.tipoEmision = tipoEmision;
    }

    public String getDomicilioComercial() {
        return domicilioComercial;
    }

    public void setDomicilioComercial(String domicilioComercial) {
        this.domicilioComercial = domicilioComercial;
    }

    
    
}

