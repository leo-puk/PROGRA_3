using System;
using System.Diagnostics;
using TechShopperBO.AdministradoresWS;

namespace TechShopperBO
{
    public class AdministradorClient
    {
        private AdministradoresClient administradorWSClient;

        public AdministradorClient()
        {
            administradorWSClient = new AdministradoresClient();
        }

        public usuarioDTO IniciarSesion(string usuarioONombre, string contraseña)
        {
            return administradorWSClient.iniciarSesion(usuarioONombre, contraseña);
        }

        public int RegistrarAdministrador(string contraseña, string nombre, string email)
        {
            int resultado = administradorWSClient.registrarAdministrador(contraseña, nombre, email);

            if (resultado > 0)
            {
                EmailUtil.EnviarEmailVerificacionAdministrador(email, nombre);
            }


            return resultado;
        }

        public int ActualizarAdministrador(int idAdministrador, string contraseña, string nombre, string email)
        {
            return administradorWSClient.actualizarAdministrador(idAdministrador, contraseña, nombre, email);
        }

        public int EliminarAdministrador(int idAdministrador)
        {
            return administradorWSClient.eliminarAdministrador(idAdministrador);
        }

        public administradorDTO ObtenerAdministradorPorId(int idAdministrador)
        {
            return administradorWSClient.obtenerAdministradorPorId(idAdministrador);
        }



        public int ActualizarEstadoConexion(int idAdministrador, string nuevoEstado)
        {
            return administradorWSClient.actualizarEstadoConexion(idAdministrador, nuevoEstado);
        }
    }
}