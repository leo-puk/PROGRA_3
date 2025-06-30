using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using TechShopperBO.ClientesWS;

namespace TechShopperBO
{
    public class ClienteClient
    {
        private ClientesClient clientesWSClient;

        public ClienteClient()
        {
            clientesWSClient = new ClientesClient();
        }

        public usuarioDTO iniciarSesion(string usuarioOCorreo, string contraseña)
        {
            return clientesWSClient.iniciarSesion(usuarioOCorreo, contraseña);
        }
        public int RegistrarCliente(clienteDTO c)
        {
            int resultado = clientesWSClient.registrarCliente(
                c.contraseña,
                c.nombre,
                c.email,
                c.direccion,
                c.telefono,
                c.infoTarjetaCredito,
                c.balanceCuenta
            );

            if (resultado > 0)
            {
                EmailUtil.EnviarEmailVerificacionCliente(c.email, c.nombre);
            }


            return resultado;
        }

        public int ActualizarCliente(clienteDTO c)
        {
            return clientesWSClient.actualizarCliente(
                c.idUsuario,
                c.contraseña,
                c.estadoConexion.ToString(),
                c.nombre,
                c.direccion,
                c.telefono,
                c.email,
                c.infoTarjetaCredito,
                c.balanceCuenta
            );
        }

        public int EliminarCliente(int idCliente)
        {
            return clientesWSClient.eliminarCliente(idCliente);
        }

        public void EnviarCorreoPedido(int idCliente)
        {

            clienteDTO c = new clienteDTO();
            c = clientesWSClient.obtenerClientePorId(idCliente);
            EmailUtil.EnviarEmailVerificacionPedido(c.email, c.nombre);

        }

        public clienteDTO ObtenerClientePorId(int idCliente)
        {
            return clientesWSClient.obtenerClientePorId(idCliente);
        }

        public int ActualizarEstadoConexionCliente(int idCliente, string nuevoEstado)
        {
            return clientesWSClient.actualizarEstadoConexionCliente(idCliente, nuevoEstado);
        }

        public int ActualizarBalanceCliente(int idCliente, double nuevoBalance)
        {
            return clientesWSClient.actualizarBalanceCliente(idCliente, nuevoBalance);
        }

        public int InsertarCarrito(int idUsuario, int idProducto, int cantidad)
        {
            return clientesWSClient.insertarCarrito(idUsuario, idProducto, cantidad);
        }
        public List<carritoItemsDTOSoap> MostrarCarritoDeCliente(int idUsuario)
        {
            // Validación inicial del parámetro
            if (idUsuario <= 0)
            {
                return new List<carritoItemsDTOSoap>();
            }

            try
            {
                var lista = clientesWSClient.mostrarCarritoDeCliente(idUsuario);
                return lista?.ToList() ?? new List<carritoItemsDTOSoap>();
            }
            catch (Exception ex)
            {
                // Log del error
                System.Diagnostics.Debug.WriteLine($"Error al obtener carrito: {ex.Message}");
                return new List<carritoItemsDTOSoap>(); // Retorna lista vacía en caso de error
            }
        }

        public int RealizarCompra(int idUsuario, List<int> idItemSeleccionados)
        {
            return clientesWSClient.realizarCompra(idUsuario, idItemSeleccionados.ToArray());
        }

        public clienteDTO ObtenerClientePorEmail(string email)
        {
            return (clienteDTO)clientesWSClient.obtenerClientePorCorreo(email);
        }
    }
}
