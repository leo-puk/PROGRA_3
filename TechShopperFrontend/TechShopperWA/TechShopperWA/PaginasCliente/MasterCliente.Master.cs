using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using TechShopperBO;
using TechShopperBO.CarritosWS;
using TechShopperBO.ClientesWS;
using TechShopperBO.ProductosWS;

//using TechShopperWA.ReferenciaCliente;

namespace TechShopperWA.PaginasCliente
{
	public partial class MasterCliente : System.Web.UI.MasterPage
	{
        //private clienteDTO cliente;
        private List<carritoItemsDTOSoap> carrito;

        protected void Page_Init(object sender, EventArgs e)
        {
            //siempre compruebo la sesion
            if (Session["Usuario"] != null)
            {
                // Usuario autenticado
                divLogueado.Visible = true;
                divNoLogueado.Visible = false;

                //cada vez que carga la pagina debe poner el valor real del carrito
                var clienteCarrito = new ClienteClient();
                carrito = clienteCarrito.MostrarCarritoDeCliente((int)Session["IdUsuario"]);
                Session["Carrito"] = carrito;
                //se ha iniciado sesión
                mostrarItemsCarrito();
            } else
            {
                // Usuario autenticado
                divLogueado.Visible = false;
                divNoLogueado.Visible = true;
            }


        }
		protected void Page_Load(object sender, EventArgs e)
		{
            
        }

        private void mostrarItemsCarrito()
        {
            //editar lbl de navbar
            lblCarritoCount.Text = obtenerCantidadItemsCarrito();

        }
        private string obtenerCantidadItemsCarrito()
        {
            if(carrito == null)
            {
                return "0";
            } else
            {
                return carrito.Count().ToString();

            }
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {

        }

        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            Session["Usuario"] = null;
            Session["Carrito"] = null;
            Response.Redirect("../InicionSesion/IniciarSesion.aspx");
        }

        protected void Categoria_Click(object sender, EventArgs e)
        {
            var btn = (LinkButton)sender;
            int idCategoria = int.Parse(btn.CommandArgument);
            string nombreCategoria = btn.Text;

            // Aquí tu lógica para manejar el click
            
            // Por ejemplo:
            Response.Redirect($"/Productos/Categoria.aspx?id={idCategoria}&nombre={Server.UrlEncode(nombreCategoria)}");

        }

        

        public class Categoria
        {
            public int Id { get; set; }
            public string Nombre { get; set; }
        }

    }
}
