using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TechShopperBO.UsuariosWS;

namespace TechShopperBO
{
    public class UsuarioClient
    {
        private UsuariosClient usuariossWSClient;

        public UsuarioClient()
        {
            usuariossWSClient = new UsuariosClient();
        }

        public usuarioDTO ObtenerUsuarioPorEmail(string email)
        {
            return usuariossWSClient.obtenerUsuarioPorCorreo(email);
        }

        public int ActualizarUsuario(int idAdministrador, string contraseña, string nombre, string email)
        {
            return usuariossWSClient.actualizarUsuario(idAdministrador, contraseña, nombre, email);
        }
    }
}
