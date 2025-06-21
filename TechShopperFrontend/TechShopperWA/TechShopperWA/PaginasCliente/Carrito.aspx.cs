using System;
using System.Collections.Generic;
using System.Linq;
using TechShopperWA.CarritoItemsWS;
using TechShopperWA.CarritosWS;

namespace TechShopperWA.PaginasCliente
{
    public partial class Carrito : System.Web.UI.Page
    {
        private CarritosWS.carritoDTO carrito;
        private List<carritoItemsDTO> items;

        protected void Page_Init(object sender, EventArgs e)
        {
            if (Session["Acceso"] != null)
            {
                var _carrito = Session["Carrito"];
                carrito = (CarritosWS.carritoDTO)_carrito;
            } else
            {
                Response.Redirect("../InicionSesion/IniciarSesion.aspx");
            }
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
            //var carrito = Session["Carrito"] as List<ProductoCarrito> ?? new List<ProductoCarrito>();
            //rptCarrito.DataSource = carrito;
            //rptCarrito.DataBind();

            //decimal total = carrito.Sum(p => p.Precio * p.Cantidad);
            //lblTotal.Text = $"Total: ${total:N2}";
        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            var btn = (System.Web.UI.WebControls.Button)sender;
            int id = int.Parse(btn.CommandArgument);

            //var carrito = Session["Carrito"] as List<ProductoCarrito>;
            //if (carrito != null)
            //{
            //    carrito.RemoveAll(p => p.Id == id);
            //    Session["Carrito"] = carrito;
            //}

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
