using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperBO.ProductosWS;
using TechShopperBO;

namespace TechShopperWA
{
    public partial class Productos : System.Web.UI.Page
    {



        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
            {
                Response.Redirect("/InicionSesion/IniciarSesion.aspx");
                return;
            }



            if (!IsPostBack)
            {
                CargarCategorias();
                CargarProductos();
            }
        }

        private void CargarCategorias()
        {
            ddlCategoria.DataSource = Enum.GetNames(typeof(categoriaDTO));
            ddlCategoria.DataBind();
            ddlCategoria.Items.Insert(0, new ListItem("-- Todas las categoría --", ""));
        }


        private void CargarProductos(string filtro = "")
        {

            ProductoClient client = new ProductoClient();
            List<productoDTO> productos = client.ListarTodos();

            // Filtro por nombre (si se ha ingresado uno)
            if (!string.IsNullOrEmpty(filtro))
            {
                productos = productos
                    .Where(p => p.nombre.ToLower().Contains(filtro.ToLower()))
                    .ToList();
            }

            // Filtro por categoría
            string categoriaSeleccionada = ddlCategoria.SelectedValue;
            if (!string.IsNullOrEmpty(categoriaSeleccionada))
            {
                productos = productos
                    .Where(p => p.categoria.ToString() == categoriaSeleccionada.ToUpper())
                    .ToList();
            }

            gvProducto.DataSource = productos;
            gvProducto.DataBind();
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            string filtro = txtBuscar.Text.Trim();
            CargarProductos(filtro);
        }
        protected void btnModificar_Click(object sender, EventArgs e)
        {
            var btn = (LinkButton)sender;
            int productoId = int.Parse(btn.CommandArgument);
            Response.Redirect($"AgregarProductos.aspx?id={productoId}");
        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            var btn = (LinkButton)sender;
            int productoId = int.Parse(btn.CommandArgument);

            ProductoClient client = new ProductoClient();
            client.EliminarProducto(productoId);

            CargarProductos();
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            Response.Redirect("AgregarProductos.aspx");
        }

        protected void btnVer_Click(object sender, EventArgs e)
        {
            var btn = (LinkButton)sender;
            int productoId = int.Parse(btn.CommandArgument);
            Response.Redirect($"VerProducto.aspx?id={productoId}");
        }

        protected void ddlCategoria_SelectedIndexChanged(object sender, EventArgs e)
        {
            CargarProductos(txtBuscar.Text.Trim());
        }

        protected void gvProducto_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvProducto.PageIndex = e.NewPageIndex;
            CargarProductos();
        }

        protected void btnVistaClientes_Click(object sender, EventArgs e)
        {
            Response.Redirect("../PaginasCliente/VistaProductosCliente.aspx");
        }
    }
}