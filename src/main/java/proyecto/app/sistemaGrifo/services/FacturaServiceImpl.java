package proyecto.app.sistemaGrifo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.*;
import proyecto.app.sistemaGrifo.repository.ClienteDao;
import proyecto.app.sistemaGrifo.repository.DetallefacturaDao;
import proyecto.app.sistemaGrifo.repository.FacturaDao;
import proyecto.app.sistemaGrifo.repository.UsuarioDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {
    private static Logger log= LoggerFactory.getLogger(FacturaServiceImpl.class);

    @Autowired
    private FacturaDao facturaDao;


    @Autowired
    private TipoFacturaImpl tipoFacturaService;

    @Autowired

    private UsuarioDao usuarioDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private DetallefacturaDao detallefacturaDao;
    @Override
    public Factura findById(Long id) {
        return facturaDao.findById(id).orElse(null);
    }

    @Override
    public List<Factura> findAll() {
        return (List<Factura>) facturaDao.findAll();
    }

    @Override
    public void deleteById(Long id) {

        facturaDao.deleteById(id);

    }

    @Override
    public Factura save(Factura factura) {
        Long id=factura.getTipoFactura().getId();
        Optional<TipoFactura> tipoFacturaBuscar=Optional.ofNullable(tipoFacturaService.findById(factura.getTipoFactura().getId()));

        if(tipoFacturaBuscar.isPresent()){
            TipoFactura tipofactura=tipoFacturaService.findById(id);
            // Obtener el valor actual del campo 'numero' como cadena
            String numeroActual = tipofactura.getNumero();

            // Convertir la cadena a un número entero
            int numeroEntero = Integer.parseInt(numeroActual);

            // Incrementar el número en uno
            numeroEntero++;

            // Formatear el número resultante como una cadena de 6 caracteres con ceros a la izquierda
            String nuevoNumero = String.format("%06d", numeroEntero);

            // Actualizar el campo 'numero' con el nuevo valor
            tipofactura.setNumero(nuevoNumero);

            //tipoDocumentoBuscado.setNumero(tipoDocumento.getNumero());


            tipoFacturaService.save(tipofactura);


        }
        try{

            Factura facturaNueva = new Factura();
            facturaNueva.setSubtotal(factura.getSubtotal());
            facturaNueva.setIgv(factura.getIgv());
            facturaNueva.setTotal(factura.getTotal());
            facturaNueva.setNroDocumento(factura.getTipoFactura().getSerie().concat("-").concat(factura.getTipoFactura().getNumero()));
            facturaNueva.setCliente(factura.getCliente());
            facturaNueva.setUsuario(factura.getUsuario());


            List<DetalleFactura> detallesNuevos = factura.getDetalleFactura().stream()
                    .map(detalleFactura -> {
                        DetalleFactura detalleNuevo = new DetalleFactura();
                        detalleNuevo.setFactura(facturaNueva);
                        detalleNuevo.setImporte(detalleFactura.getImporte());
                        detalleNuevo.setProducto(detalleFactura.getProducto());
                        detalleNuevo.setCantidad(detalleFactura.getCantidad());
                        return detalleNuevo;
                    })
                    .collect(Collectors.toList());

            facturaNueva.setDetalleFactura(detallesNuevos);

            facturaNueva.setTipoFactura(factura.getTipoFactura());

            return facturaDao.save(facturaNueva);
        }catch (Exception e){
            throw new RuntimeException("No se encontró el usuario con ID: ");
        }


















        //NUEVO



//        Factura facturaNuevo=new Factura();
//
//            facturaNuevo.setSubtotal(factura.getSubtotal());
//            facturaNuevo.setIgv(factura.getIgv());
//            facturaNuevo.setTotal(factura.getTotal());
//           // log.info("total: ",factura.getTotal());
//
//           facturaNuevo.setNroDocumento(factura.getTipoFactura().getSerie().concat("-").concat(factura.getTipoFactura().getNumero()));
//
//
//            facturaNuevo.setCliente(factura.getCliente());
//
//           facturaNuevo.setUsuario(factura.getUsuario());
//           facturaNuevo.setDetalleFactura(factura.getDetalleFactura());
//
//           for(DetalleFactura facturDetalle:factura.getDetalleFactura()){
//               DetalleFactura detalleFactura=new DetalleFactura();
//
//               detalleFactura.setFactura(facturaNuevo);
//               detalleFactura.setImporte(facturDetalle.getImporte());
//               detalleFactura.setProducto(facturDetalle.getProducto());
//               detalleFactura.setCantidad(facturDetalle.getCantidad());
//
//               System.out.println("cantidad en omplemenete: " + detalleFactura.getCantidad());
//
//
//
//               facturaNuevo.getDetalleFactura().add(detalleFactura);
//               //System.out.println("cantidad en omplemenete: " + facturaNuevo.getDetalleFactura().size());
//
//
//           }
//
//           facturaNuevo.setTipoFactura(factura.getTipoFactura());

        //return facturaDao.save(facturaNuevo)







       // return facturaDao.save(factura);
    }

    @Override
    public Page<Factura> findAll(Pageable pageable) {
        return facturaDao.findAll(pageable);
    }

    @Override
    public Factura findByNroDocumento(String nro) {
        return facturaDao.findByNroDocumento(nro);
    }
}
