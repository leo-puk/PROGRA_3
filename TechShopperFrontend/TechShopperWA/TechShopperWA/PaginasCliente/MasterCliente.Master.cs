using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using TechShopperBO.CarritosWS;
using TechShopperBO.ClientesWS;

//using TechShopperWA.ReferenciaCliente;

namespace TechShopperWA.PaginasCliente
{
	public partial class MasterCliente : System.Web.UI.MasterPage
	{
        //private clienteDTO cliente;
        private List<carritoItemsDTO> carrito;

        protected void Page_Init(object sender, EventArgs e)
        {
            //siempre compruebo la sesion
            if (Session["Usuario"] != null)
            {
                var _carrito = Session["Carrito"];
                carrito = (List<carritoItemsDTO>)_carrito;
                //se ha iniciado sesión
                mostrarItemsCarrito();
            }


        }
		protected void Page_Load(object sender, EventArgs e)
		{
            ////siempre compruebo la sesion
            //if (Session["Usuario"] != null) 
            //{ 
            
            //    //se ha iniciado sesión
            //    mostrarItemsCarrito();
            //}

            
                CargarCategorias();
            
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
            Response.Redirect("VistaProductosCliente.aspx");
        }

        protected void CargarCategorias()
        {
            //string connection...
            //    using (SqlConnection)...
            //        select ...
            //        while(read())...

            //CARGO LA LISTA DE CATEGORÍAS DESDE EL BO

            //Creo el elemento para cada categoría
            // Simulando datos de categorías (deberías obtenerlos de tu BD)
            var categorias = new List<Categoria>
    {
        new Categoria { Id = 1, Nombre = "Electrónicos" },
        new Categoria { Id = 2, Nombre = "Computación" },
        new Categoria { Id = 3, Nombre = "Hogar" }
    };

            foreach (var categoria in categorias)
            {
                var li = new HtmlGenericControl("li");

                var linkButton = new LinkButton
                {
                    ID = $"btnCat_{categoria.Id}",
                    Text = categoria.Nombre,
                    CssClass = "dropdown-item",
                    CommandArgument = categoria.Id.ToString()
                };
                linkButton.Click += Categoria_Click;

                li.Controls.Add(linkButton);
                menuCategorias.Controls.Add(li);
            }
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
