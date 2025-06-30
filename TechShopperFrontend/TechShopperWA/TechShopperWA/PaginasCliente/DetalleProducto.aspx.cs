using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using TechShopperBO;
using TechShopperBO.ClientesWS;

namespace TechShopperWA.PaginasCliente
{
    public partial class DetalleProducto : System.Web.UI.Page
    {
        private TechShopperBO.ClientesWS.usuarioDTO usuario;
        private TechShopperBO.ProductosWS.productoDTO producto;
        private List<carritoItemsDTOSoap> carrito;
        //private clienteDTO cliente;
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
            if (Session["Usuario"] != null)
            {
                //estoy conectado con usuario nomas :D
                usuario = (usuarioDTO)Session["Usuario"];
            }

        }
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void mostrarDatos()
        {
            lblNombre.InnerText = producto.nombre;
            lblPrecio.InnerText = "S/" + producto.precio.ToString();
            lblDescripcion.InnerText = producto.descripcion;
            mainImage.Src = producto.imagenURL;

            lblMarca.InnerText = producto.marca;

        }

        protected void btnComprarAhora_Click(object sender, EventArgs e)
        {
            //leer la cantidad
            leerCantidad();
            if (Session["Usuario"] != null)
            {
                aniadirProdCarrito();
                Response.Redirect("Carrito.aspx");
            }
            else
            {
                //mostrar popup de qe tienes que iniciar sesión.
                Response.Redirect("../InicionSesion/IniciarSesion.aspx");
            }


        }

        private void aniadirProdCarrito()
        {

            var cliente = new ClienteClient();
            cliente.InsertarCarrito(usuario.idUsuario, producto.idProducto, cantidad);
            Session["Carrito"] = cliente.MostrarCarritoDeCliente((int)Session["IdUsuario"]);

        }

        private void leerCantidad()
        {
            cantidad = int.TryParse(txtCantidad.Text, out int result) ? result : 1;

        }

        protected void btnAgregarCarrito_Click(object sender, EventArgs e)
        {
            leerCantidad();
            aniadirProdCarrito();
            ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('Se agrego el rpoducto al carrito');", true);
            Response.Redirect(Request.RawUrl);


        }
    }
}
