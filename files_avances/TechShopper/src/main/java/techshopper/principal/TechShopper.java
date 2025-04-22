
package techshopper.principal;
import techshopper.dao.*;
import techshopper.dto.*;
//import techshopper.config.DBManager;

public class TechShopper {

    public static void main(String[] args) {
        //Probaremos el CRUD de Producto
        ProductoDTO prod = new ProductoDTO();
        prod.setIdProducto(1);
        prod.setNombre("barracuda");
        prod.setCategoria(CategoriaDTO.AURICULARES);
        prod.setMarca("ryzen");
        prod.setPrecio(112.23);
        prod.setStock(5);
        ProductoDAO prodCRUD = new ProductoDAOImp(); //Con esto controlo todo el crud de producto
        
        //Insertamos producto
        prodCRUD.Insertar(prod);
    }
}
