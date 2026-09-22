package com.tp.facturacion;

import java.util.Date;

import com.tp.facturacion.base.AuditoriaApp;
import com.tp.facturacion.model.Articulo;
import com.tp.facturacion.model.Cliente;
import com.tp.facturacion.model.CondicionIva;
import com.tp.facturacion.model.Contacto;
import com.tp.facturacion.model.Domicilio;
import com.tp.facturacion.model.FacturaVenta;
import com.tp.facturacion.model.FacturaVentaDetalle;
import com.tp.facturacion.model.ListaPrecio;
import com.tp.facturacion.model.ListaPrecioArticulo;
import com.tp.facturacion.model.Marca;
import com.tp.facturacion.model.PuntoVenta;
import com.tp.facturacion.model.Rubro;
import com.tp.facturacion.model.TipoMoneda;
import com.tp.facturacion.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main( String[] args ){

        //Iniciar contenedor JPA
      EntityManagerFactory emf = null;
      EntityManager em = null;

        try{
        System.out.println("Iniciando JPA...");
        emf = Persistence.createEntityManagerFactory("FacturacionPU");
        em = emf.createEntityManager();

            //inicio transaccion
            System.out.println("Iniciando transaccion...");
            em.getTransaction().begin();

            //Intanciamos objetos necesarios
            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setUsuario("admin");
            usuarioAdmin.setClave("1234");
            usuarioAdmin.setNombre("Lucas");
            usuarioAdmin.setApellido("Timmermann");
            em.persist(usuarioAdmin);//persistimos el objeto para poder usarlo en auditoria

            Contacto contacto = new Contacto("lucas@example.com", "555-0100", "555-0101");
            em.persist(contacto);

            Domicilio domicilio = new Domicilio("Av. Central", "123");
            em.persist(domicilio);

            Cliente cliente = new Cliente("20-12345678-9", "Cliente consumidor final", contacto, domicilio);
            establecerAuditoria(cliente, usuarioAdmin);
            em.persist(cliente);

            Rubro rubro = new Rubro("Informática", 1);
            establecerAuditoria(rubro, usuarioAdmin);
            em.persist(rubro);

            Marca marca = new Marca("Marca Demo", 1);
            establecerAuditoria(marca, usuarioAdmin);
            em.persist(marca);

            CondicionIva condicionIva = new CondicionIva(5, "Consumidor Final");
            establecerAuditoria(condicionIva, usuarioAdmin);
            em.persist(condicionIva);

            TipoMoneda tipoMoneda = new TipoMoneda("PES", "Peso argentino", "$" );
            establecerAuditoria(tipoMoneda, usuarioAdmin);
            em.persist(tipoMoneda);

            PuntoVenta puntoVenta = new PuntoVenta();
            puntoVenta.setNumero(1);
            puntoVenta.setDescripcion("Local Central");
            puntoVenta.setTipoEmision("ELECTRONICA");
            puntoVenta.setDomicilioComercial("Av. Central 123");
            establecerAuditoria(puntoVenta, usuarioAdmin);
            em.persist(puntoVenta); //persistimos el objeto para poder usarlo en FacturaVenta

            Articulo articulo = new Articulo();
            articulo.setRubro(rubro);
            articulo.setCodigo("ART-001");
            articulo.setDenominacion("Notebook Pro 15");
            articulo.setMarca(marca);
            establecerAuditoria(articulo, usuarioAdmin);
            em.persist(articulo); //persistimos el objeto para poder usarlo en FacturaVentaDetalle

            ListaPrecio listaPrecio = new ListaPrecio();
            listaPrecio.setCodigo("LP-001");
            listaPrecio.setDenominacion("Precio minorista");
            establecerAuditoria(listaPrecio, usuarioAdmin);
            em.persist(listaPrecio);

            ListaPrecioArticulo listaPrecioArticulo = new ListaPrecioArticulo();
            listaPrecioArticulo.setListaPrecio(listaPrecio);
            listaPrecioArticulo.setPrecioVenta(1500.0);
            listaPrecioArticulo.setArticulo(articulo);
            establecerAuditoria(listaPrecioArticulo, usuarioAdmin);
            em.persist(listaPrecioArticulo);

            //Instanciomos la cabecera de la Factura (obejto FActuraVenta)
            FacturaVenta factura = new FacturaVenta();
            factura.setNumero(1024L);
            factura.setFechaEmision(new Date());
            factura.setEstado("EMITIDA");
            factura.setCliente(cliente);
            factura.setCondicionIva(condicionIva);
            factura.setTipoMoneda(tipoMoneda);
            factura.setImporteCobrado(0.0);
            factura.setImporteSaldo(1815.0);
            factura.setImporteTotal(1500.0);
            factura.setResultadoAfip("PENDIENTE");

            //datos obligatorios de auditoria
            establecerAuditoria(factura, usuarioAdmin);
            factura.setPuntoVenta(puntoVenta);

            //Intanciamos el detalle(ocjeto FacturaVentaDetalle) y lo agregamos a la cabecera de la factura
            FacturaVentaDetalle detalle1 = new FacturaVentaDetalle();
            detalle1.setDescripcion("Venta Notebook Pro");
            detalle1.setCantidad(1);
            detalle1.setListaPrecioArticulo(listaPrecioArticulo);
            detalle1.setPrecioUnitario(listaPrecioArticulo.getPrecioVenta());
            detalle1.setPorcentajeBonificacion(0.0);
            detalle1.setImporteNeto(detalle1.getCantidad() * detalle1.getPrecioUnitario());
            detalle1.setImporteIva(detalle1.getImporteNeto() * 0.21);
            detalle1.setImporteSubtotal(detalle1.getImporteNeto() + detalle1.getImporteIva());  
            
            //Agregamos el detalle a la cabecera de la factura
            factura.addDetalle(detalle1);

            //persistimos toda la factura junto al detalle factura unicamente desde factura
            em.persist(factura);

            //finalizamos la transaccion
            em.getTransaction().commit();
            System.out.println("Factura de venta generada correctamente con numero: " + factura.getNumero());
  
        }catch(Exception e){
          //si alfo da error se hace rollback de la transaccion
          if(em.getTransaction().isActive()){
            em.getTransaction().rollback();
          }
          e.printStackTrace();
        }finally{
            //cerramos el EntityManager y el EntityManagerFactory
          if (em != null) {
            em.close();
          }
          if (emf != null) {
            emf.close();
          }
        }

    }

      private static void establecerAuditoria(AuditoriaApp entidad, Usuario usuario) {
        Date ahora = new Date();
        entidad.setFechaAlta(ahora);
        entidad.setFechaModificacion(ahora);
        entidad.setUsuarioCarga(usuario);
        entidad.setUsuarioModificacion(usuario);
      }
    

}
