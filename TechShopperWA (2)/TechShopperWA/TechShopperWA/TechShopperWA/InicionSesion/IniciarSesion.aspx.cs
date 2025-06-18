using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperWA.AdministradoresWS;
using TechShopperWA.ProductosWS;

namespace TechShopperWA.InicionSesion
{
	public partial class IniciarSesion1 : System.Web.UI.Page
	{
		//valores que se necesitan para la modificación de un usuario (inactivo a activo)
		private string usuario;
		private string contraseña;
        private string ip;


		//si se olvidó la contraseña se le debe mandar un correo a su correo de un número aleatoreo que deberá ingresar 
		//de ahí podrá modificar su contraseña

		//si quiero registrarme es otra página.

		protected void Page_Load(object sender, EventArgs e)
		{
			//valido si está conectado

		}

        //al clickearel boton de iniciar se debe validar los datos y modificar el estado del usuario 
        protected void btnIngresar_Click(object sender, EventArgs e)
        {
            usuario = txtUsuario.Text.Trim();
            contraseña = txtContraseña.Text.Trim();

            try
            {
                AdministradoresWS.AdministradoresClient cliente = new AdministradoresWS.AdministradoresClient();
                var usuarioDTO = cliente.iniciarSesion(usuario, contraseña);

                if (usuarioDTO != null && usuarioDTO.idUsuario > 0)
                {
                    // Usuario válido
                    Session["Acceso"] = true;
                    Session["Usuario"] = usuarioDTO.nombre;
                    Session["IdUsuario"] = usuarioDTO.idUsuario;
                    Response.Redirect("../Index.aspx");
                }
                else
                {
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                        "alert('Usuario o contraseña incorrectos.');", true);
                }
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('Error en la conexión con el servidor.');", true);
            }
        }
        
        


        private bool esUsuarioValido(string usuario, string contraseña)
        {
            //se tiene que verificar que el estado esté en desconectado y que los datos sean correctos 
            //si ya esta conectado no se valida

            //ACA IRÍA LA VALIDACIÓN CON EL BO
            return true;
        }
    }
}