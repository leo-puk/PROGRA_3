using System;
using System.Collections.Generic;
using TechShopperBO.CarritoItemsWS;
using TechShopperBO.CarritosWS;

namespace TechShopperBO
{
    public class CarritoItemClient
    {
        private CarritoItemsClient carritoItemsWSClient;

        public CarritoItemClient()
        {
            carritoItemsWSClient = new CarritoItemsClient(); 
        }

        /*public int Insertar(int idCarrito, int idProducto, int cantidad, double precioUnitario, DateTime fechaRegistro)
        {
            return carritoItemsWSClient.insertarCarritoItem(idCarrito, idProducto, cantidad, precioUnitario, fechaRegistro);
        }

        public int Modificar(int idItemCarrito, int idCarrito, int idProducto, int cantidad, double precioUnitario, DateTime fechaRegistro)
        {
            return carritoItemsWSClient.modificarCarritoItem(idItemCarrito, idCarrito, idProducto, cantidad, precioUnitario, fechaRegistro);
        }*/

        public int Eliminar(int idItemCarrito)
        {
            return carritoItemsWSClient.eliminarCarritoItem(idItemCarrito);
        }

        public carritoItemsDTO ObtenerPorId(int idItemCarrito)
        {
            return carritoItemsWSClient.obtenerCarritoItemPorId(idItemCarrito);
        }

        public List<carritoItemsDTO> ListarTodos()
        {
            var items = carritoItemsWSClient.listarTodosLosCarritoItems();
            return items != null ? new List<carritoItemsDTO>(items) : new List<carritoItemsDTO>();
        }

        public List<carritoItemsDTO> ListarPorCarrito(int idCarrito)
        {
            var items = carritoItemsWSClient.listarItemsPorCarrito(idCarrito);
            return items != null ? new List<carritoItemsDTO>(items) : new List<carritoItemsDTO>();
        }

        public double CalcularTotalCarrito(int idCarrito)
        {
            return carritoItemsWSClient.calcularTotalCarrito(idCarrito);
        }

        public int AgregarProductoAlCarrito(int idCarrito, int idProducto, int cantidad, double precioUnitario)
        {
            return carritoItemsWSClient.agregarProductoAlCarrito(idCarrito, idProducto, cantidad, precioUnitario);
        }


        public int EliminarProductoDelCarrito(int idCarrito, int idProducto)
        {
            return carritoItemsWSClient.eliminarProductoDelCarrito(idCarrito, idProducto);
        }

        public int ActualizarCantidadProducto(int idCarrito, int idProducto, int nuevaCantidad)
        {
            return carritoItemsWSClient.actualizarCantidadProducto(idCarrito, idProducto, nuevaCantidad);
        }

        public int VaciarCarrito(int idCarrito)
        {
            return carritoItemsWSClient.vaciarCarrito(idCarrito);
        }
    }
}