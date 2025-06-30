using System;
using System.Web.UI;
using TechShopperBO;
using TechShopperBO.AdministradoresWS;
using TechShopperBO.ClientesWS;


namespace TechShopperWA.InicionSesion
{
    public partial class IniciarSesion1 : System.Web.UI.Page
    {

        private string usuario;
        private string contraseña;
        private TechShopperBO.AdministradoresWS.usuarioDTO usuarioAdmin;
        private TechShopperBO.ClientesWS.usuarioDTO usuarioCliente;
        private TechShopperBO.ClientesWS.carritoItemsDTOSoap carrito; //Cada usuario tendra un carrito
        protected void Page_Init(object sender, EventArgs e)
        {
            usuarioAdmin = null;
            usuarioCliente = null;
        }

        protected void Page_Load(object sender, EventArgs e)
        {


        }

        //Se validan los datos y modifican el estado del usuario 
        protected void btnIngresar_Click(object sender, EventArgs e)
        {

            usuario = txtUsuario.Text.Trim();
            contraseña = txtContraseña.Text.Trim();


            iniciarSesionUsuario(usuario, contraseña);

        }

        private void iniciarSesionUsuario(string usuario, string contraseña)
        {

            //primero se intenta iniciar sesión con admin
            var clientAdmin = new AdministradorClient();
            var usuarioAdmin_ = clientAdmin.IniciarSesion(usuario, contraseña);


            if (usuarioAdmin_ == null)
            {
                //se intenta iniciar sesion con cliente
                var clienteClient = new ClienteClient();
                var clienteCliente_ = clienteClient.iniciarSesion(usuario, contraseña);


                if (clienteCliente_ == null)
                {
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('El usuario o contraseña son incorrectos');", true);
                    return;
                }
                else
                {

                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('se pudo conectar con cliente');", true);
                    usuarioCliente = clienteCliente_ as TechShopperBO.ClientesWS.usuarioDTO;
                    iniciarSesionCliente();
                }
            }
            else
            {
                usuarioAdmin = usuarioAdmin_ as TechShopperBO.AdministradoresWS.usuarioDTO;
                //si se encontró como admin
                iniciarSesionAdmin();
            }
        }

        private void iniciarSesionAdmin()
        {
            Session["Acceso"] = true;
            ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('inciaste con admin", true);
            Session["Usuario"] = usuarioAdmin;
            Session["IdUsuario"] = usuarioAdmin.idUsuario;
            Response.Redirect("../Index.aspx");
        }

        private void iniciarSesionCliente()
        {


            var clienteCliente = new ClienteClient();
            Session["Usuario"] = usuarioCliente;
            Session["IdUsuario"] = usuarioCliente.idUsuario;
            Session["Carrito"] = clienteCliente.MostrarCarritoDeCliente(usuarioCliente.idUsuario);
            Response.Redirect("../PaginasCliente/VistaProductosCliente.aspx");
        }
    }
}



