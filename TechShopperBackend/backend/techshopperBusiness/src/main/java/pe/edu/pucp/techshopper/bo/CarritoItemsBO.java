package pe.edu.pucp.techshopper.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.techshopper.dao.CarritoItemsDAO;
import pe.edu.pucp.techshopper.daoImp.CarritoItemsDAOImp;
import pe.edu.pucp.techshopper.model.CarritoDTO;
import pe.edu.pucp.techshopper.model.CarritoItemsDTO;
import pe.edu.pucp.techshopper.model.ProductoDTO;

public class CarritoItemsBO {
    
    private final CarritoItemsDAO carritoItemsDAO;
    
    public CarritoItemsBO() {
        this.carritoItemsDAO = new CarritoItemsDAOImp();
    }
    
    public Integer insertar(Integer idCarrito, Integer idProducto, 
                          Integer cantidad, Double precioUnitario, 
                          LocalDateTime fechaRegistro) {
        if (idCarrito == null || idCarrito <= 0 || idProducto == null || 
            idProducto <= 0 || cantidad == null || cantidad <= 0 || 
            precioUnitario == null || precioUnitario <= 0 || 
            fechaRegistro == null) {
            return -1;
        }
        
        CarritoDTO carrito = new CarritoDTO();
        carrito.setIdCarrito(idCarrito);
        
        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(idProducto);
        
        CarritoItemsDTO item = new CarritoItemsDTO();
        item.setCarrito(carrito);
        item.setProducto(producto);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(precioUnitario);
        item.setFechaRegistro(fechaRegistro);
        
        return carritoItemsDAO.insertar(item);
    }
    
    public Integer modificar(Integer idItemCarrito, Integer idCarrito, 
                           Integer idProducto, Integer cantidad, 
                           Double precioUnitario, LocalDateTime fechaRegistro) {
        if (idItemCarrito == null || idItemCarrito <= 0 || idCarrito == null || 
            idCarrito <= 0 || idProducto == null || idProducto <= 0 || 
            cantidad == null || cantidad <= 0 || precioUnitario == null || 
            precioUnitario <= 0 || fechaRegistro == null) {
            return -1;
        }
        
        CarritoDTO carrito = new CarritoDTO();
        carrito.setIdCarrito(idCarrito);
        
        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(idProducto);
        
        CarritoItemsDTO item = new CarritoItemsDTO();
        item.setIdCarritoItems(idItemCarrito);
        item.setCarrito(carrito);
        item.setProducto(producto);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(precioUnitario);
        item.setFechaRegistro(fechaRegistro);
        
        return carritoItemsDAO.modificar(item);
    }
    
    public Integer eliminar(Integer idItemCarrito) {
        if (idItemCarrito == null || idItemCarrito <= 0) {
            return -1;
        }
        
        CarritoItemsDTO item = new CarritoItemsDTO();
        item.setIdCarritoItems(idItemCarrito);
        
        return carritoItemsDAO.eliminar(item);
    }
    
    public CarritoItemsDTO obtenerPorId(Integer idItemCarrito) {
        if (idItemCarrito == null || idItemCarrito <= 0) {
            return null;
        }
        return carritoItemsDAO.obtenerPorId(idItemCarrito);
    }
    
    public ArrayList<CarritoItemsDTO> listarTodos() {
        return carritoItemsDAO.listarTodos();
    }
    
    // Métodos adicionales que podrían ser útiles para la lógica de negocio
    
    public ArrayList<CarritoItemsDTO> listarPorCarrito(Integer idCarrito) {
        if (idCarrito == null || idCarrito <= 0) {
            return new ArrayList<>();
        }
        
        ArrayList<CarritoItemsDTO> todosItems = listarTodos();
        ArrayList<CarritoItemsDTO> itemsFiltrados = new ArrayList<>();
        
        for (CarritoItemsDTO item : todosItems) {
            if (item.getCarrito().getIdCarrito().equals(idCarrito)) {
                itemsFiltrados.add(item);
            }
        }
        
        return itemsFiltrados;
    }
    
    /***Otros métodos***/
    
    public Double calcularTotalCarrito(Integer idCarrito) {
        if (idCarrito == null || idCarrito <= 0) {
            return 0.0;
        }
        
        ArrayList<CarritoItemsDTO> items = listarPorCarrito(idCarrito);
        Double total = 0.0;
        
        for (CarritoItemsDTO item : items) {
            total += item.getPrecioUnitario() * item.getCantidad();
        }
        
        return total;
    }
    
    
    /**
    * Añade un producto al carrito o incrementa su cantidad si ya existe
    * @param idCarrito ID del carrito
    * @param idProducto ID del producto a añadir
    * @param cantidad Cantidad a añadir
    * @param precioUnitario Precio actual del producto
    * @return ID del item creado o modificado, -1 si hay error
    */
   public Integer agregarProductoAlCarrito(Integer idCarrito, Integer idProducto, 
                                         Integer cantidad, Double precioUnitario) {
       if (idCarrito == null || idProducto == null || cantidad == null || cantidad <= 0 || 
           precioUnitario == null || precioUnitario <= 0) {
           return -1;
       }

       // Verificar si el producto ya está en el carrito
       CarritoItemsDTO itemExistente = buscarItemEnCarrito(idCarrito, idProducto);

       if (itemExistente != null) {
           // Actualizar cantidad si el producto ya existe
           return modificar(itemExistente.getIdCarritoItems(), idCarrito, idProducto, 
                           itemExistente.getCantidad() + cantidad, 
                           precioUnitario, 
                           itemExistente.getFechaRegistro());
       } else {
           // Crear nuevo item si el producto no existe en el carrito
           return insertar(idCarrito, idProducto, cantidad, precioUnitario, LocalDateTime.now());
       }
   }

   /**
    * Busca un producto específico en el carrito
    * @param idCarrito ID del carrito
    * @param idProducto ID del producto a buscar
    * @return Item del carrito o null si no existe
    */
   public CarritoItemsDTO buscarItemEnCarrito(Integer idCarrito, Integer idProducto) {
       if (idCarrito == null || idProducto == null) {
           return null;
       }

       ArrayList<CarritoItemsDTO> items = listarPorCarrito(idCarrito);
       for (CarritoItemsDTO item : items) {
           if (item.getProducto().getIdProducto().equals(idProducto)) {
               return item;
           }
       }
       return null;
   }

   /**
    * Elimina un producto específico del carrito
    * @param idCarrito ID del carrito
    * @param idProducto ID del producto a eliminar
    * @return 1 si se eliminó correctamente, -1 si hay error
    */
   public Integer eliminarProductoDelCarrito(Integer idCarrito, Integer idProducto) {
       if (idCarrito == null || idProducto == null) {
           return -1;
       }

       CarritoItemsDTO item = buscarItemEnCarrito(idCarrito, idProducto);
       if (item != null) {
           return eliminar(item.getIdCarritoItems());
       }
       return -1;
   }

   /**
    * Actualiza la cantidad de un producto en el carrito
    * @param idCarrito ID del carrito
    * @param idProducto ID del producto
    * @param nuevaCantidad Nueva cantidad (si es <= 0, se elimina el producto)
    * @return ID del item modificado, -1 si hay error
    */
   public Integer actualizarCantidadProducto(Integer idCarrito, Integer idProducto, Integer nuevaCantidad) {
       if (idCarrito == null || idProducto == null || nuevaCantidad == null) {
           return -1;
       }

       CarritoItemsDTO item = buscarItemEnCarrito(idCarrito, idProducto);
       if (item == null) {
           return -1;
       }

       if (nuevaCantidad <= 0) {
           return eliminar(item.getIdCarritoItems());
       } else {
           return modificar(item.getIdCarritoItems(), idCarrito, idProducto, 
                          nuevaCantidad, item.getPrecioUnitario(), item.getFechaRegistro());
       }
   }

   /**
    * Vacía completamente un carrito eliminando todos sus items
    * @param idCarrito ID del carrito a vaciar
    * @return Número de items eliminados, -1 si hay error
    */
   public Integer vaciarCarrito(Integer idCarrito) {
       if (idCarrito == null || idCarrito <= 0) {
           return -1;
       }

       ArrayList<CarritoItemsDTO> items = listarPorCarrito(idCarrito);
       int eliminados = 0;

       for (CarritoItemsDTO item : items) {
           if (eliminar(item.getIdCarritoItems()) > 0) {
               eliminados++;
           }
       }

       return eliminados;
   }
   
   
   //NUEVOSSSSS
   
   public Double calcularMontoTotalCarrito(Integer idCarrito){
       
       return carritoItemsDAO.calcularMontoTotalCarrito(idCarrito);
   }

    
}