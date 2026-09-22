package com.tp.facturacion.model;
import com.tp.facturacion.base.AuditoriaApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "articulo")
public class Articulo extends AuditoriaApp {
    @ManyToOne
    @JoinColumn (name = "rubro_id", nullable = false)
    private Rubro rubro;
    @Column (nullable = false)
    private String codigo;
    @Column (nullable = false)
    private String denominacion;
    @ManyToOne
    @JoinColumn (name = "marca_id", nullable = false)
    private Marca marca;

    //metodo constructor vacio
    public Articulo() {
    }
    //metodo constructor con parametros
    public Articulo(Rubro rubro, String codigo, String denominacion, Marca marca) {
        this.rubro = rubro;
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.marca = marca;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    
    
}