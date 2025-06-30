using System;
using System.Collections.Generic;
using System.Linq;
using TechShopperBO.CarritosWS;
using TechShopperBO;
using TechShopperBO.ClientesWS;
using System.Collections;
using System.Web.UI.WebControls;
using static System.Net.Mime.MediaTypeNames;

namespace TechShopperWA.PaginasCliente
{
    public partial class Carrito : System.Web.UI.Page
    {
        private TechShopperBO.CarritosWS.carritoDTO carrito;
        private List<carritoItemsDTOSoap> carritoItems;

        protected void Page_Init()
        {
            
      
                if (Session["Usuario"] != null)
                {
                    cargarCarrito();
                    mostrarItems();
                    
                }
                else
                {
                    //señal al usuario que debe iniciar sesión
                    Response.Redirect("../InicionSesion/IniciarSesion.aspx");
                }

            
        }

        

        private void mostrarItems()
        {
            if (carritoItems == null || carritoItems.Count == 0)
                return;
            var clienteProd = new ProductoClient();
            var itemsParaMostrar = carritoItems.Select(item => new
            {
                Id = item.idProducto,
                Nombre = clienteProd.ObtenerPorId(item.idProducto).nombre,
                ImagenUrl = clienteProd.ObtenerPorId(item.idProducto).imagenURL, // se mapea con Eval("ImagenUrl")
                precioUnitario = item.precioUnitario,
                Cantidad = item.cantidad
            }).ToList();

            rptCarrito.DataSource = itemsParaMostrar;
            rptCarrito.DataBind();

            //mostramos el total calculado (solo la suma de items)
            double total = carritoItems.Sum(item => item.precioUnitario * (double)item.cantidad);
            lblTotal.Text = $"Total: S/{total:F2}";
        }


        private void cargarCarrito()
        {
            var carritoItems_ = Session["Carrito"];
            carritoItems = (List<carritoItemsDTOSoap>)carritoItems_;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        
        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string[] datos = btn.CommandArgument.Split('|');
            int id = int.Parse(datos[0]);
            int cantidad = int.Parse(datos[1]);
            double precioUnitario = double.Parse(datos[2]);
            int idCarrito = carritoItems.ElementAt(0).idCarrito;
            
            //actualizar el monto del carrito
            var clienteCarritoItem = new CarritoItemClient();
            var carrito_ = new CarritoClient();
            carrito_.ActualizarPrecio(idCarrito, carrito.precio - precioUnitario*(double)cantidad);
            clienteCarritoItem.EliminarProductoDelCarrito(idCarrito, id);


            Response.Redirect("Carrito.aspx");
        }

        protected void btnHacerPedido_Click(object sender, EventArgs e)
        {

            //mandar a siguiente pestaña (pedido)
            
        }

        protected void btnMas_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string[] argumentos = btn.CommandArgument.Split('|');

            int id = int.Parse(argumentos[0]);
            int cantidad = int.Parse(argumentos[1]);
            double precioUnitario = double.Parse(argumentos[2]);
            int idCarrito = carritoItems.ElementAt(0).idCarrito;


            //se agrega a carrito item
            var carritoItem = new CarritoItemClient();
            carritoItem.AgregarProductoAlCarrito(idCarrito, id, 1, precioUnitario);//esto no actualiza el monto del carrito

            //actualizar el monto del carrito
            var carrito_ = new CarritoClient();
            carrito_.ActualizarPrecio(idCarrito, carrito.precio + precioUnitario);

            Response.Redirect("Carrito.aspx");
        }

        protected void btnMenos_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string[] argumentos = btn.CommandArgument.Split('|');

            int id = int.Parse(argumentos[0]);
            int cantidad = int.Parse(argumentos[1]);
            double precioUnitario = double.Parse(argumentos[2]);
            int idCarrito = carritoItems.ElementAt(0).idCarrito;


            //se agrega a carrito item
            var carritoItem = new CarritoItemClient();
            carritoItem.ActualizarCantidadProducto(idCarrito, id, cantidad - 1);

            //actualizar el monto del carrito
            var carrito_ = new CarritoClient();
            carrito_.ActualizarPrecio(idCarrito, carrito.precio - precioUnitario);
            // Lógica: incrementar cantidad, actualizar carrito, etc.
            Response.Write($"+ ID: {id}, Cantidad: {cantidad}, Precio: {precioUnitario}");
            Response.Redirect("Carrito.aspx");
        }

        
        protected void btnFinalizarCompra_Click(object sender, EventArgs e)
        {
            Response.Redirect("ResumenPedido.aspx");
        }
    }
}
