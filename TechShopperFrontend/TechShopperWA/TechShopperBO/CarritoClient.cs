using System.Collections.Generic;
using TechShopperBO.CarritosWS;

namespace TechShopperBO
{
    public class CarritoClient
    {
        private CarritosClient carritosWSClient;

        public CarritoClient()
        {
            carritosWSClient = new CarritosClient("CarritosPort");
        }

        public int CrearCarrito(int idUsuario)
        {
            return carritosWSClient.crearCarrito(idUsuario);
        }

        public int ActualizarPrecio(int idCarrito, double nuevoPrecio)
        {
            return carritosWSClient.actualizarPrecioCarrito(idCarrito, nuevoPrecio);
        }

        public int EliminarCarrito(int idCarrito)
        {
            return carritosWSClient.eliminarCarrito(idCarrito);
        }

        public carritoDTO ObtenerPorId(int idCarrito)
        {
            return carritosWSClient.obtenerCarritoPorId(idCarrito);
        }

        public List<carritoDTO> ListarTodos()
        {
            var lista = carritosWSClient.listarTodosLosCarritos();
            return lista != null ? new List<carritoDTO>(lista) : new List<carritoDTO>();
        }

        public double MontoAPagar(int idCarrito)
        {
            return carritosWSClient.montoAPagar(idCarrito);
        }
    }
}