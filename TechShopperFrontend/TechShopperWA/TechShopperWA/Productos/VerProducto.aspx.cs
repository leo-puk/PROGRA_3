using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperBO;
using TechShopperBO.ProductosWS;


namespace TechShopperWA
{
    public partial class VerProducto : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Request.QueryString["id"] != null)
                {
                    int id = int.Parse(Request.QueryString["id"]);
                    CargarProducto(id);
                }
            }
        }

        private void CargarProducto(int id)
        {
            var client = new ProductoClient();
            productoDTO producto = client.ObtenerPorId(id); // método del WS

            if (producto != null)
            {
                lblCodigo.Text = producto.idProducto.ToString();
                lblNombre.Text = producto.nombre;
                lblDescripcion.Text = producto.descripcion;
                lblCategoria.Text = producto.categoria.ToString();
                lblMarca.Text = producto.marca;
                lblStockDisponible.Text = producto.stockDisponible.ToString();
                lblStockMinimo.Text = producto.stockMinimo.ToString();
                lblPrecio.Text = "S/ " + producto.precio.ToString("F2");

                if (!string.IsNullOrWhiteSpace(producto.imagenURL) && producto.imagenURL.StartsWith("http"))
                {
                    phImagen.Controls.Add(new Literal
                    {
                        Text = $"<img src='{producto.imagenURL}' class='img-thumbnail' width='200' height='200' alt='Imagen del producto' />"
                    });
                }
                else
                {
                    phImagen.Controls.Add(new Literal
                    {
                        Text = "<div class='d-flex align-items-center justify-content-center bg-light border rounded' style='width:200px; height:200px;'>" +
                               "<i class='bi bi-image text-muted' style='font-size: 3rem;'></i>" +
                               "</div>"
                    });
                }

                if (producto.usuario == null)
                {
                    lblIdAdmin.Text = "0";
                }
                else
                {
                    lblIdAdmin.Text = producto.usuario.idUsuario.ToString();
                }

            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Productos.aspx");
        }
    }
}