using System.Collections.Generic;
using TechShopperWA.ProductosWS;  // Aquí están tus clases SOAP

namespace TechShopperWA
{
    public class ProductoClient
    {
        private ProductosClient productosWSClient;

        public ProductoClient()
        {
            productosWSClient = new ProductosClient(); // Constructor generado automáticamente
        }

        public List<productoDTO> ListarTodos()
        {
            var lista = productosWSClient.listarTodosProductos();
            return lista != null ? new List<productoDTO>(lista) : new List<productoDTO>();
        }

        public productoDTO ObtenerPorId(int id)
        {
            return productosWSClient.obtenerProductoPorId(id);
        }

        public int RegistrarProducto(productoDTO p)
        {
            return productosWSClient.registrarProductosssss(
                p.precio,p.stockDisponible,p.stockMinimo,
                p.nombre, p.marca, p.categoria.ToString(),
                p.descripcion,p.usuario.idUsuario
            );
        }

        public int ActualizarProducto(productoDTO p)
        {
            return productosWSClient.actualizarProducto(
                p.idProducto, p.precio, p.stockDisponible,
                p.nombre, p.marca, p.categoria.ToString(),
                p.descripcion
            );
        }

        public int EliminarProducto(int id)
        {
            return productosWSClient.eliminarProducto(id);
        }

        public List<productoDTO> Buscar(string nombre, string categoria, string marca)
        {
            return new List<productoDTO>(productosWSClient.listarPor3criterios(nombre, categoria, marca));
        }
    }
}