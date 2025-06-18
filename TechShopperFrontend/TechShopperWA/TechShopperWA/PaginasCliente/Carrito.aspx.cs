using System;
using System.Collections.Generic;
using System.Linq;

namespace TechShopperWA.PaginasCliente
{
    public partial class Carrito : System.Web.UI.Page
    {
        public class ProductoCarrito
        {
            public int Id { get; set; }
            public string Nombre { get; set; }
            public decimal Precio { get; set; }
            public int Cantidad { get; set; }
            public string ImagenUrl { get; set; }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarCarrito();
            }
        }

        private void CargarCarrito()
        {
            var carrito = Session["Carrito"] as List<ProductoCarrito> ?? new List<ProductoCarrito>();
            rptCarrito.DataSource = carrito;
            rptCarrito.DataBind();

            decimal total = carrito.Sum(p => p.Precio * p.Cantidad);
            lblTotal.Text = $"Total: ${total:N2}";
        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            var btn = (System.Web.UI.WebControls.Button)sender;
            int id = int.Parse(btn.CommandArgument);

            var carrito = Session["Carrito"] as List<ProductoCarrito>;
            if (carrito != null)
            {
                carrito.RemoveAll(p => p.Id == id);
                Session["Carrito"] = carrito;
            }

            CargarCarrito();
        }

        protected void btnFinalizarCompra_Click(object sender, EventArgs e)
        {
            Session["Carrito"] = null;
            lblTotal.Text = "Gracias por su compra 🎉";
            rptCarrito.DataSource = null;
            rptCarrito.DataBind();
        }
    }
}
