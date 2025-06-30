using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperBO;
using TechShopperBO.MovimientosStockWS;
using TechShopperBO.ProductosWS;
using usuarioDTO = TechShopperBO.ProductosWS.usuarioDTO;


namespace TechShopperWA
{
    public partial class WebForm1 : System.Web.UI.Page
    {


        protected void Page_Load(object sender, EventArgs e)
        {

            if (Session["Acceso"] == null)
            {
                Response.Redirect("/InicionSesion/IniciarSesion.aspx");
                return;
            }
            if (!IsPostBack)
            {
                ddlCategoria.DataSource = Enum.GetNames(typeof(TechShopperBO.ProductosWS.categoriaDTO));
                ddlCategoria.DataBind();
                ddlCategoria.Items.Insert(0, new ListItem("-- Seleccione una categoría --", ""));

                if (Request.QueryString["id"] != null)
                {
                    int id = int.Parse(Request.QueryString["id"]);

                    var client = new ProductoClient();
                    var productos = client.ListarTodos();

                    var prod = client.ObtenerPorId(id);



                    if (prod != null)
                    {
                        imgVistaPrevia.ImageUrl = !string.IsNullOrEmpty(prod.imagenURL) ? prod.imagenURL : "/img/placeholder.png"; //Vista previa
                        txtCodigo.Text = prod.idProducto.ToString();
                        txtNombre.Text = prod.nombre;
                        txtDescripcion.Text = prod.descripcion;
                        txtPrecio.Text = prod.precio.ToString("0.00");
                        ddlCategoria.SelectedValue = prod.categoria.ToString();
                        txtMarca.Text = prod.marca;
                        txtStockDisponible.Text = prod.stockDisponible.ToString();
                        txtStockMinimo.Text = prod.stockMinimo.ToString();
                        txtIdAdmin.Text = prod.usuario != null ? prod.usuario.idUsuario.ToString() : "";
                        if (prod.imagenURL != null)
                        {
                            txtImg.Text = prod.imagenURL;
                        }

                    }

                }
                else
                {
                    int idAdmin = (int)Session["IdUsuario"];
                    txtIdAdmin.Text = idAdmin.ToString();
                }

            }
        }

        protected void Page_Init(object sender, EventArgs e)
        {
        }

        private void CargarEntidad()
        {

        }


        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Productos.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            var client = new ProductoClient();
            var prod = new TechShopperBO.ProductosWS.productoDTO
            {
                idProducto = string.IsNullOrEmpty(txtCodigo.Text) ? 0 : int.Parse(txtCodigo.Text),
                nombre = txtNombre.Text,
                descripcion = txtDescripcion.Text,
                precio = Convert.ToDouble(txtPrecio.Text),
                categoria = (TechShopperBO.ProductosWS.categoriaDTO)Enum.Parse(typeof(TechShopperBO.ProductosWS.categoriaDTO), ddlCategoria.SelectedValue),
                marca = txtMarca.Text,
                stockDisponible = int.Parse(txtStockDisponible.Text),
                stockMinimo = int.Parse(txtStockMinimo.Text),
                imagenURL = string.IsNullOrEmpty(txtImg.Text) ? null : txtImg.Text,
                usuario = new usuarioDTO
                {
                    idUsuario = int.Parse(txtIdAdmin.Text)
                }
            };

            if (string.IsNullOrEmpty(txtCodigo.Text))
            {
                client.RegistrarProducto(prod);
            }
            else
            {
                var clientMov = new MovimientoStockClient();
                var clientProd = new ProductoClient();

                // Obtener el producto actual de la base de datos
                var productoActual = clientProd.ObtenerPorId(prod.idProducto);
                int stockAnterior = productoActual.stockDisponible;
                int stockNuevo = prod.stockDisponible;

                // Verificar si hay cambio en el stock
                if (stockAnterior != stockNuevo)
                {
                    int cantidad = Math.Abs(stockNuevo - stockAnterior);
                    int hayVariacion = stockNuevo > stockAnterior ? 1 : -1; // 1 = Aumento, -1 = Disminución
                    int idUsuario = (int)Session["IdUsuario"];
                    int idProd = prod.idProducto;

                    clientMov.RegistrarMovimiento(cantidad, idProd, idUsuario, hayVariacion);
                }

                client.ActualizarProducto(prod);
            }

            Response.Redirect("Productos.aspx");
        }
    }

}