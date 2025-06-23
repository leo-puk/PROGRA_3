using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperWA.CarritoItemsWS;
using TechShopperWA.ReferenciaCliente;
using TechShopperWA.ProductosWS;
using TechShopperWA.CarritosWS;

namespace TechShopperWA.PaginasCliente
{
    public partial class DetalleProducto : System.Web.UI.Page
    {
        private ProductosWS.productoDTO producto;
        private CarritosWS.carritoDTO carrito;
        private clienteDTO cliente;
        private int cantidad;
        protected void Page_Init(object sender, EventArgs e)
        {
            if (Request.QueryString["id"] != null)
            {
                int productoId;
                if (int.TryParse(Request.QueryString["id"], out productoId))
                {
                    //llamada al wsdl
                    var client = new ProductoClient();
                    producto = client.ObtenerPorId(productoId);
                    mostrarDatos();
                }
                else
                {
                    // ID no válido
                    Response.Redirect("~/Error.aspx?msg=ID de producto no válido");
                }
            }
            else
            {
                // No hay ID en la URL
                Response.Redirect("~/Error.aspx?msg=Producto no especificado");
            }
            //valida la session y carga el carrito 
            if (Session["Acceso"] != null)
            {
                var _carrito = Session["Carrito"];
                carrito = (CarritosWS.carritoDTO)_carrito;
                Response.Write(carrito.idCarrito.ToString());
            }
            
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            
        }
        protected void mostrarDatos()
        {
            lblNombre.InnerText = producto.nombre;
            lblPrecio.InnerText = "$" + producto.precio.ToString() ;
            lblDescripcion.InnerText = producto.descripcion;
            lblMarca.InnerText = producto.marca;
            
        }

        protected void btnComprarAhora_Click(object sender, EventArgs e)
        {
            //leer la cantidad
            leerCantidad();
            if (Session["Acceso"]!=null)
            {
                aniadirProdCarrito();
                Response.Redirect("Carrito.aspx");
            } else
            {
                //añadir una alerta bacán
                Response.Redirect("../InicionSesion/IniciarSesion.aspx");
            }
                
            
        }

        private void aniadirProdCarrito()
        {
            //Intercambiar por lógica de añadir producto desde el cliente
            var clientCarritoItems = new CarritoItemsClient();
            if(carrito!=null)
                clientCarritoItems.agregarProductoAlCarrito(carrito.idCarrito, producto.idProducto, cantidad, producto.precio);
            
            
            
        }

        private void leerCantidad()
        {
            cantidad = int.TryParse(txtCantidad.Text, out int result) ? result : 1;

        }

        protected void btnAgregarCarrito_Click(object sender, EventArgs e)
        {
            leerCantidad();
            aniadirProdCarrito();
        }
    }
}
        