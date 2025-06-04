using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using static TechShopperWA.Productos;

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
                ddlCategoria.DataSource = Enum.GetNames(typeof(CategoriaEnum));
                ddlCategoria.DataBind();
                ddlCategoria.Items.Insert(0, new ListItem("-- Seleccione una categoría --", ""));

                if (Request.QueryString["id"] != null)
                {
                    int id = int.Parse(Request.QueryString["id"]);
                    var productos = Session["productos"] as List<Producto>;

                    //var client = new ProductoServiceSoapClient();
                    //var productos = client.listarTodosLosProductos();

                    var prod = productos.FirstOrDefault(p => p.ProductoId == id);
                    if (prod != null)
                    {
                        txtCodigo.Text = prod.ProductoId.ToString();
                        txtNombre.Text = prod.Nombre;
                        txtDescripcion.Text = prod.Descripcion;
                        txtPrecio.Text = prod.Precio.ToString("0.00");
                        ddlCategoria.SelectedValue = prod.Categoria;
                        txtMarca.Text = prod.Marca;
                        txtStock.Text = prod.Stock.ToString();
                    }
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

            //var client = new ProductoServiceSoapClient();
            //var productos = client.listarTodosLosProductos();

            //string nombre = txtNombre.Text;
            //string descripcion = txtDescripcion.Text;
            //double precio = decimal.Parse(txtPrecio.Text);
            //string categoria = ddlCategoria.SelectedValue;
            //string marca = txtMarca.Text;
            //int stock = int.Parse(txtStock.Text);

            var productos = Session["productos"] as List<Producto> ?? new List<Producto>();
            Producto prod;

            if (!string.IsNullOrEmpty(txtCodigo.Text)) // Modificar
            {
                int idProducto = int.Parse(txtCodigo.Text);
                prod = productos.FirstOrDefault(p => p.ProductoId == idProducto);
                //esto se quita
                if (prod != null)
                {
                    prod.Nombre = txtNombre.Text;
                    prod.Descripcion = txtDescripcion.Text;
                    prod.Precio = decimal.Parse(txtPrecio.Text);
                    prod.Categoria = ddlCategoria.SelectedValue;
                    prod.Marca = txtMarca.Text;
                    prod.Stock = int.Parse(txtStock.Text);

                }

                //Esto se descomenta
                //var client = new ProductoServiceSoapClient();
                //client.actualizarProducto(idProducto, precio, stock, nombre, marca, categoria, descripcion);
            }
            else // Agregar nuevo
            {
                //esto tambien se quita
                int nuevoId = productos.Count > 0 ? productos.Max(p => p.ProductoId) + 1 : 1;
                prod = new Producto
                {
                    ProductoId = nuevoId,
                    Nombre = txtNombre.Text,
                    Descripcion = txtDescripcion.Text,
                    Precio = decimal.Parse(txtPrecio.Text),
                    Categoria = ddlCategoria.SelectedValue,
                    Marca = txtMarca.Text,
                    Stock = int.Parse(txtStock.Text)
                };
                //Hasta aca



                //var client = new ProductoServiceSoapClient();
                //client.registrarProducto(precio, stock, nombre, marca, categoria, descripcion);

                productos.Add(prod);
            }

            //Se quita esto
            Session["productos"] = productos;
            Response.Redirect("Productos.aspx");
        }
    }

}