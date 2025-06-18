using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperWA.ProductosWS;

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
                ddlCategoria.DataSource = Enum.GetNames(typeof(categoriaDTO));
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
                        txtCodigo.Text = prod.idProducto.ToString();
                        txtNombre.Text = prod.nombre;
                        txtDescripcion.Text = prod.descripcion;
                        txtPrecio.Text = prod.precio.ToString("0.00");
                        ddlCategoria.SelectedValue = prod.categoria.ToString();
                        txtMarca.Text = prod.marca;
                        txtStockDisponible.Text = prod.stockDisponible.ToString();
                        txtStockMinimo.Text = prod.stockMinimo.ToString();
                        txtIdAdmin.Text = prod.usuario.idUsuario.ToString();
                        txtIdAdmin.Text = prod.usuario != null ? prod.usuario.idUsuario.ToString() : "";
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

            var prod = new productoDTO
            {
                idProducto = string.IsNullOrEmpty(txtCodigo.Text) ? 0 : int.Parse(txtCodigo.Text),
                nombre = txtNombre.Text,
                descripcion = txtDescripcion.Text,
                precio = Convert.ToDouble(txtPrecio.Text), 
                categoria = (categoriaDTO)Enum.Parse(typeof(categoriaDTO), ddlCategoria.SelectedValue),
                marca = txtMarca.Text,
                stockDisponible = int.Parse(txtStockDisponible.Text),
                stockMinimo = int.Parse(txtStockMinimo.Text),
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
                client.ActualizarProducto(prod);
            }

            Response.Redirect("Productos.aspx");
        }
    }

}