using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperBO;

namespace TechShopperWA.InicionSesion
{
    public partial class OlvideContra : System.Web.UI.Page
    {

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void btnActualizar_Click(object sender, EventArgs e)
        {
            string email = txtEmail.Text.Trim();
            string nuevaClave = txtNuevaClave.Text.Trim();
            string confirmarClave = txtConfirmarClave.Text.Trim();

            if (nuevaClave != confirmarClave)
            {
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Las contraseñas no coinciden.');", true);
                return;
            }

            if (nuevaClave.Length < 8)
            {
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('La nueva contraseña debe tener al menos 8 caracteres.');", true);
                return;
            }

            var client = new UsuarioClient();
            var usuario = client.ObtenerUsuarioPorEmail(email); //Busca por correo

            if (usuario != null)
            {
                int result = client.ActualizarUsuario(usuario.idUsuario, nuevaClave, usuario.nombre, usuario.email);
                if (result >= 0)
                {

                    ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Contraseña actualizada correctamente.'); window.location='IniciarSesion.aspx';", true);
                }
                else
                {
                    ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Ocurrió un error al actualizar la contraseña.');", true);
                }
            }
            else
            {
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('El correo ingresado no está registrado.');", true);
            }
        }
    }
}