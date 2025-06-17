using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace TechShopperWA.PaginasCliente
{
	public partial class MasterCliente : System.Web.UI.MasterPage
	{
		protected void Page_Load(object sender, EventArgs e)
		{
            if (!IsPostBack)
            {
                CargarCategorias();
            }
		}


        protected void btnBuscar_Click(object sender, EventArgs e)
        {

        }

        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {

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
        new Categoria ( 1, "Electrónicos" ),
        new Categoria (2, "Computación"),
        new Categoria (3, "Hogar")
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
            private int Id;
            private string Nombre;

            public Categoria(int id, string nombre)
            {
                Id = id;
                Nombre = nombre;
            }
            
        }

    }
}
