using System;
using System.Collections.Generic;
using System.Linq;
using TechShopperBO.CarritosWS;
using TechShopperBO;
using TechShopperBO.ClientesWS;
using System.Collections;
using System.Web.UI.WebControls;

namespace TechShopperWA.PaginasCliente
{
    public partial class Carrito : System.Web.UI.Page
    {
        private TechShopperBO.CarritosWS.carritoDTO carrito;
        private List<carritoItemsDTO> carritoItems;

        protected void Page_Init(object sender, EventArgs e)
        {
            if (Session["Usuario"] != null)
            {
                cargarCarrito();
                mostrarItems();
            } else
            {
                //señal al usuario que debe iniciar sesión
                Response.Redirect("../InicionSesion/IniciarSesion.aspx");
            }
        }

        private void mostrarItems()
        {
            if (carritoItems == null || carritoItems.Count == 0)
                return;

            var itemsParaMostrar = carritoItems.Select(item => new
            {
                Id = item.producto.idProducto,
                Nombre = item.producto.nombre,
                ImagenUrl = item.producto.imagenURL, // se mapea con Eval("ImagenUrl")
                precioUnitario = item.precioUnitario,
                Cantidad = 1
            }).ToList();

            rptCarrito.DataSource = itemsParaMostrar;
            rptCarrito.DataBind();

            var carrito_ = new CarritoClient();
            carrito = carrito_.ObtenerPorId(carritoItems.ElementAt(0).carrito.idCarrito);
            double total = carrito.precio;
            lblTotal.Text = $"Total: ${total:N2}";
        }


        private void cargarCarrito()
        {
            var carritoItems_ = Session["Carrito"];
            carritoItems = (List<carritoItemsDTO>)carritoItems_;
            
            
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            
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

            //CargarCarrito();
        }

        protected void btnHacerPedido_Click(object sender, EventArgs e)
        {

            //mandar a siguiente pestaña (pedido)
        }

        protected void btnMas_Click(object sender, EventArgs e)
        {
            var btn = (Button)sender;
            int id = int.Parse(btn.CommandArgument);

            // Aquí aumentarías la cantidad del producto con ese ID
            // Simulado por ahora
            Response.Write($"Aumentar cantidad del producto {id}");

            //CargarCarrito(); // Recargar el frontend
        }

        protected void btnMenos_Click(object sender, EventArgs e)
        {
            var btn = (Button)sender;
            int id = int.Parse(btn.CommandArgument);

            // Aquí disminuirías la cantidad del producto con ese ID
            // Simulado por ahora
            Response.Write($"Disminuir cantidad del producto {id}");

            //CargarCarrito(); // Recargar el frontend
        }
        protected void btnFinalizarCompra_Click(object sender, EventArgs e)
        {

        }
    }
}
