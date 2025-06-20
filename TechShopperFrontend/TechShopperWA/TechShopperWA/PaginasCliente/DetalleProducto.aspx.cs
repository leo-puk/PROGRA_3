using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperWA.ProductosWS;

namespace TechShopperWA.PaginasCliente
{
    public partial class DetalleProducto : System.Web.UI.Page
    {
        productoDTO producto;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
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
            }
        }
        protected void mostrarDatos()
        {
            lblNombre.InnerText = producto.nombre;
            lblPrecio.InnerText = "$" + producto.precio.ToString() ;
            lblPrecioOriginal.InnerText = "$" + (producto.precio*1.25).ToString();
            lblDescripcion.InnerText = producto.descripcion;
            lblMarca.InnerText = producto.marca;
            
        }

        protected void btnComprarAhora_Click(object sender, EventArgs e)
        {
            //añade el producto al carrito y lo manda allí

        }

        protected void btnAgregarCarrito_Click(object sender, EventArgs e)
        {

        }
    }
}
        