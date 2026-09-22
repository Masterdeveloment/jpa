package com.tp.facturacion.model;
import com.tp.facturacion.base.AuditoriaApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "condicion_iva")
public class CondicionIva extends AuditoriaApp {
    @Column (nullable = false)
    private int codigoAfip;
    @Column (nullable = false)
    private String denominacion;
    
    //metodo constructorvacio

    public CondicionIva() {}

    //metodo constructor con parametros
    
    public CondicionIva(int codigoAfip, String denominacion) {
        this.codigoAfip = codigoAfip;
        this.denominacion = denominacion;
    }


    public int getCodigoAfip() {
        return codigoAfip;
    }


    public void setCodigoAfip(int codigoAfip) {
        this.codigoAfip = codigoAfip;
    }


    public String getDenominacion() {
        return denominacion;
    }


    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    
}
