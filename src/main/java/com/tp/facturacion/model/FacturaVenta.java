package com.tp.facturacion.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tp.facturacion.base.AuditoriaApp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "factura_venta")
public class FacturaVenta extends AuditoriaApp {
 
    @Column (nullable= false)   
    private Long numero;
    @Column (nullable= false)
    private Date fechaEmision;
    @ManyToOne
    @JoinColumn (name = "cliente_id", nullable = false)
    private Cliente cliente;
    @ManyToOne 
    @JoinColumn (name = "condicion_iva_id", nullable = false)
    private CondicionIva condicionIva;
    @ManyToOne 
    @JoinColumn (name = "tipo_moneda_id", nullable = false)
    private TipoMoneda tipoMoneda;
    @ManyToOne 
    @JoinColumn (name = "punto_venta_id", nullable = false)
    private PuntoVenta puntoVenta;
    @Column (nullable= false)
    private double importeCobrado;
    @Column (nullable= false)
    private double importeSaldo;
    @Column(nullable = false)
    private double importeTotal;
    private String cae;
    private Date caeFechaVencimiento;
    @Column (nullable= false)
    private String resultadoAfip;
    private String motivoRechazo;
    @Column(nullable = false)
    private String estado;
    private Date fechaAnulacion;
    private String observaciones;
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<FacturaVentaDetalle> detalles = new ArrayList<>();

    //metodo de ayuda para agregar dettales al arratlist
    public void addDetalle(FacturaVentaDetalle detalle) {
        detalles.add(detalle);
        detalle.setFactura(this);
    }

    //metodo contructor vacio

    public FacturaVenta() {
    }

    //metodo constructor con parametros
    
    public FacturaVenta(Long numero, Date fechaEmision, Cliente cliente, CondicionIva condicionIva,
            TipoMoneda tipoMoneda, PuntoVenta puntoVenta, double importeCobrado, double importeSaldo,
            double importeTotal, String cae, Date caeFechaVencimiento, String resultadoAfip, String motivoRechazo,
            String estado, Date fechaAnulacion, String observaciones, List<FacturaVentaDetalle> detalles) {
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.cliente = cliente;
        this.condicionIva = condicionIva;
        this.tipoMoneda = tipoMoneda;
        this.puntoVenta = puntoVenta;
        this.importeCobrado = importeCobrado;
        this.importeSaldo = importeSaldo;
        this.importeTotal = importeTotal;
        this.cae = cae;
        this.caeFechaVencimiento = caeFechaVencimiento;
        this.resultadoAfip = resultadoAfip;
        this.motivoRechazo = motivoRechazo;
        this.estado = estado;
        this.fechaAnulacion = fechaAnulacion;
        this.observaciones = observaciones;
        this.detalles = detalles;
    }


    //metodos getters

    public Long getNumero() {
        return numero;
    }



    public Date getFechaEmision() {
        return fechaEmision;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public CondicionIva getCondicionIva() {
        return condicionIva;
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public PuntoVenta getPuntoVenta() {
        return puntoVenta;
    }


    public double getImporteCobrado() {
        return importeCobrado;
    }


    public double getImporteSaldo() {
        return importeSaldo;
    }


    public double getImporteTotal() {
        return importeTotal;
    }


    public String getCae() {
        return cae;
    }


    public Date getCaeFechaVencimiento() {
        return caeFechaVencimiento;
    }


    public String getResultadoAfip() {
        return resultadoAfip;
    }


    public String getMotivoRechazo() {
        return motivoRechazo;
    }


    public String getEstado() {
        return estado;
    }


    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }


    public String getObservaciones() {
        return observaciones;
    }



    //metodos setters

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCondicionIva(CondicionIva condicionIva) {
        this.condicionIva = condicionIva;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public void setPuntoVenta(PuntoVenta puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public void setImporteCobrado(double importeCobrado) {
        this.importeCobrado = importeCobrado;
    }

    public void setImporteSaldo(double importeSaldo) {
        this.importeSaldo = importeSaldo;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public void setCae(String cae) {
        this.cae = cae;
    }

    public void setCaeFechaVencimiento(Date caeFechaVencimiento) {
        this.caeFechaVencimiento = caeFechaVencimiento;
    }

    public void setResultadoAfip(String resultadoAfip) {
        this.resultadoAfip = resultadoAfip;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

            
}
