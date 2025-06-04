using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace TechShopperWA.InicionSesion
{
	public partial class IniciarSesion1 : System.Web.UI.Page
	{
		//valores que se necesitan para la modificación de un usuario (inactivo a activo)
		private string usuario;
		private string contraseña;
        private string ip;

		public IniciarSesion1()
		{

		}

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
            // Obtener valores de los TextBox
            usuario = txtUsuario.Text.Trim();
            contraseña = txtContraseña.Text.Trim();

            try
            {
                // Aquí llamas a tu lógica de negocio (BO = Business Object)
                // Suponiendo que tienes un método esUsuarioValido(usuario, contraseña)
                if (esUsuarioValido(usuario, contraseña))
                {
                    // Redirigir si las credenciales son correctas

                    //ACTUALIZAR SESIÓN (DESDE BO)
                    //actualizarSessionUsuario(usuario);

                    //Guardamos que iniciamos sesión en esta instancia con la variable global Session
                    //Todas las páginas ahora verificarán las variables globales de la instancia front para poder acceder a ellas
                    //y estas variables solo permitiran acceder despues de pasar por inicio de sesión. Si se cierra la pagina tendrás que volver a iniciar sesión
                    Session["Acceso"] = true;
                    Session["Usuario"] = usuario;
                    
                    Response.Redirect("../Index.aspx");
                }
                else
                {
                    // Mostrar alerta si el usuario no es válido
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                        "alert('Usuario o contraseña incorrectos.');", true);
                }
            }
            catch (Exception ex)
            {
                // Mostrar mensaje de error si hubo un problema en la validación
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
            "alert('Usuario o contraseña incorrectos.');", true);
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