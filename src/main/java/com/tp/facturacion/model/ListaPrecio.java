package com.tp.facturacion.model;
import com.tp.facturacion.base.AuditoriaApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "lista_precio")
public class ListaPrecio extends AuditoriaApp {

    @Column(nullable = false)
    private String codigo;
    @Column(nullable = false)
    private String denominacion;
    
    //metodo constructor vacio

    public ListaPrecio() {
    }

    //metodo constructor con parametros
    
    public ListaPrecio(String codigo, String denominacion) {
        this.codigo = codigo;
        this.denominacion = denominacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    
}