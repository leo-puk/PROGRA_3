package pe.edu.pucp.techshopper.bo;

import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.CarritoDAO;
import pe.edu.pucp.techshopper.daoImp.CarritoDAOImp;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ClienteDTO;

public class CarritoBO {

    private final CarritoDAO carritoDAO;
    
    public CarritoBO() {
        this.carritoDAO = new CarritoDAOImp();
    }
    
    public Integer crearCarrito(Integer idCliente) {
        if (idCliente == null || idCliente <= 0) {
            return -1;
        }
        
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setPrecio(0.0); // Precio inicial en 0
        
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdUsuario(idCliente);
        carritoDTO.setUsuario(cliente);
        
        return carritoDAO.insertar(carritoDTO);
    }
    
    public Integer actualizarPrecioCarrito(Integer idCarrito, Double nuevoPrecio) {
        if (idCarrito == null || idCarrito <= 0 || nuevoPrecio == null || nuevoPrecio < 0) {
            return -1;
        }
        
        CarritoDTO carrito = carritoDAO.obtenerPorId(idCarrito);
        if (carrito == null) {
            return -1;
        }
        
        carrito.setPrecio(nuevoPrecio);
        return carritoDAO.modificar(carrito);
    }
    
    public Integer eliminarCarrito(Integer idCarrito) {
        if (idCarrito == null || idCarrito <= 0) {
            return -1;
        }
        
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setIdCarrito(idCarrito);
        
        return carritoDAO.eliminar(carritoDTO);
    }
    
    public CarritoDTO obtenerCarritoPorId(Integer idCarrito) {
        if (idCarrito == null || idCarrito <= 0) {
            return null;
        }
        return carritoDAO.obtenerPorId(idCarrito);
    }
    
    public ArrayList<CarritoDTO> listarTodosLosCarritos() {
        return carritoDAO.listarTodos();
    }
    
    
    /***Otros métodos***/
    
    public Double montoAPagar(Integer idCarrito) {
        // Validación de entrada
        if (idCarrito == null || idCarrito <= 0) {
            return 0.0;
        }

        // Obtener el carrito para verificar su existencia
        CarritoDTO carrito = carritoDAO.obtenerPorId(idCarrito);
        if (carrito == null) {
            return 0.0;
        }

        // Calcular el total de los items del carrito
        CarritoItemsBO carritoItemsBO = new CarritoItemsBO();
        ArrayList<CarritoItemsDTO> items = carritoItemsBO.listarPorCarrito(idCarrito);

        Double totalItems = 0.0;
        for (CarritoItemsDTO item : items) {
            totalItems += item.getCantidad() * item.getPrecioUnitario();
        }

        // Sumar el precio base del carrito (si existe)
        Double precioBase = carrito.getPrecio() != null ? carrito.getPrecio() : 0.0;

        return totalItems + precioBase;
    }
    
}