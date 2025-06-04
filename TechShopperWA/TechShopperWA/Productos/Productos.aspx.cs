using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace TechShopperWA
{
    public partial class Productos : System.Web.UI.Page
    {

        public class Producto
        {
            public int ProductoId { get; set; }
            public string Nombre { get; set; }
            public string Descripcion { get; set; }
            public string Categoria { get; set; }
            public string Marca { get; set; }
            public int Stock { get; set; }
            public decimal Precio { get; set; }
        }

        public enum CategoriaEnum
        {
            LAPTOP,
            PC,
            MOUSE,
            MONITOR,
            TECLADO,
            AURICULARES,
            AUDIFONOS,
            MICROFONO,
            PARLANTES
        }

        // Lista simulada (como si viniera de BD)
        private List<Producto> ObtenerProductos()
        {
            //var client = new ProductoServiceSoapClient();
            //return client.listarTodosLosProductos().ToList();

            return new List<Producto>
            {
                new Producto
                {
                    ProductoId = 1,
                    Nombre = "Laptop Lenovo",
                    Descripcion = "14'' Core i5",
                    Categoria = "LAPTOP",
                    Marca = "Lenovo",
                    Stock = 10,
                    Precio = 2800
                },
                new Producto
                {
                    ProductoId = 2,
                    Nombre = "Monitor Samsung",
                    Descripcion = "24'' LED",
                    Categoria = "MONITOR",
                    Marca = "Samsung",
                    Stock = 8,
                    Precio = 850
                },
                new Producto
                {
                    ProductoId = 3,
                    Nombre = "Teclado Logitech",
                    Descripcion = "Inalámbrico",
                    Categoria = "TECLADO",
                    Marca = "Logitech",
                    Stock = 25,
                    Precio = 120
                },
                new Producto
                {
                    ProductoId = 4,
                    Nombre = "Mouse Genius",
                    Descripcion = "USB óptico",
                    Categoria = "MOUSE",
                    Marca = "Genius",
                    Stock = 40,
                    Precio = 40
                }
            };
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Acceso"] == null)
            {
                Response.Redirect("/InicionSesion/IniciarSesion.aspx");
                return;
            }
            if (!IsPostBack)
            {


                //Cargamos el dropbox
                ddlCategoria.DataSource = Enum.GetNames(typeof(CategoriaEnum));
                ddlCategoria.DataBind();
                ddlCategoria.Items.Insert(0, new ListItem("-- Todas las Categorías --", ""));

                //Habria que quitar esta parte
                if (Session["productos"] == null)
                {
                    Session["productos"] = ObtenerProductos();
                }
                CargarProductos(); // Se llama al servicio SOAP
            }
        }

        protected void Page_Init(object sender, EventArgs e)
        {

        }

        private void CargarProductos(string filtro = "")
        {

            //var client = new ProductoServiceSoapClient();
            //List<Producto> productos = client.listarTodosLosProductos().ToList();


            List<Producto> productos = Session["productos"] as List<Producto> ?? new List<Producto>();

            if (!string.IsNullOrEmpty(filtro))
            {
                productos = productos
                    .Where(p => p.Nombre.ToLower().Contains(filtro.ToLower()))
                    .ToList();
            }

            string categoriaSeleccionada = ddlCategoria.SelectedValue;
            if (!string.IsNullOrEmpty(categoriaSeleccionada))
            {
                productos = productos
                    .Where(p => p.Categoria.ToUpper() == categoriaSeleccionada.ToUpper())
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

            //Cambiar cuando se tenga la BD disponible
            //Esta parte se quitaria
            var productos = Session["productos"] as List<Producto> ?? new List<Producto>();
            var producto = productos.FirstOrDefault(p => p.ProductoId == productoId);
            if (producto != null) productos.Remove(producto);

            Session["productos"] = productos;

            //var client = new ProductoServiceSoapClient();
            //client.eliminarProducto(productoId);

            CargarProductos();
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            Response.Redirect("AgregarProductos.aspx");
        }

        protected void ddlCategoria_SelectedIndexChanged(object sender, EventArgs e)
        {
            CargarProductos(txtBuscar.Text.Trim());
        }

    }
}