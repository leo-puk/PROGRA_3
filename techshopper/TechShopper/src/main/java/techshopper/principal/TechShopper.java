
package techshopper.principal;
import techshopper.dao.*;
import techshopper.dto.*;
import java.time.LocalDateTime;
//import techshopper.config.DBManager;

public class TechShopper {

    public static void main(String[] args) {
        //Probaremos el CRUD de Producto
        ProductoDTO prod = new ProductoDTO();
        prod.setIdProducto(1);
        prod.setNombre("Barracuda");
        prod.setCategoria(CategoriaDTO.AURICULARES);
        prod.setMarca("ryzen");
        prod.setPrecio(112.23);
        prod.setStock(5);
        ProductoDAO prodCRUD = new ProductoDAOImp(); //Con esto controlo todo el crud de producto
        
        //Insertamos producto
        prodCRUD.Insertar(prod);
        //Eliminamos producto
        prodCRUD.Eliminar(1);
        
        
        //Probamos Carrito
        CarritoDTO car = new CarritoDTO();
        car.setIdCarrito(2);
        car.setPrecio(15.12);
        CarritoDAO carCRUD = new CarritoDAOImp();
        //Insertamos carrito
        carCRUD.Insertar(car);
        
        
        //Probamos CarritoItems
        CarritoItemsDTO carItem = new CarritoItemsDTO();
        carItem.setIdCarritoItems(1);
        carItem.setCarrito(car);
        carItem.setProducto(prod);
        carItem.setCantidad(3);
        carItem.setPrecioUnitario(99.99);
        carItem.setFechaRegistro(java.time.LocalDateTime.now());
        
        CarritoItemsDAO carItemCRUD = new CarritoItemsDAOImp();
        //Insertamos el item
        carItemCRUD.Insertar(carItem);
    }
}
