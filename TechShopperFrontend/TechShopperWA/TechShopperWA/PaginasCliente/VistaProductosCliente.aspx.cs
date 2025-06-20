using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using TechShopperWA.ProductosWS;


namespace TechShopperWA.PaginasCliente
{
    public partial class Index : System.Web.UI.Page
    {
        
        public class Producto
        {
            public int Id { get; set; }
            public string Nombre { get; set; }
            public string Marca { get; set; }
            public decimal Precio { get; set; }
            public string ImagenUrl { get; set; }
        }

        // Clase modelo para Marca (hardcodeada)
        public class Marca
        {
            public int Id { get; set; }
            public string Nombre { get; set; }
        }

        protected void Page_init(object sender, EventArgs e)
        {

            Page.UnobtrusiveValidationMode = UnobtrusiveValidationMode.None;

            if (!IsPostBack)
            {
                CargarMarcas();
                CargarProductos();
            }



        }
        protected void Page_Load(object sender, EventArgs e)
        {
            

        }

        private void CargarMarcas()
        {
            try
            {
                // Datos hardcodeados de marcas
                List<Marca> marcas = new List<Marca>
                {
                    new Marca { Id = 1, Nombre = "Samsung" },
                    new Marca { Id = 2, Nombre = "Apple" },
                    new Marca { Id = 3, Nombre = "LG" },
                    new Marca { Id = 4, Nombre = "Sony" },
                    new Marca { Id = 5, Nombre = "HP" }
                };

                cblMarcas.DataSource = marcas;
                cblMarcas.DataTextField = "Nombre";
                cblMarcas.DataValueField = "Id";
                cblMarcas.DataBind();

                // Seleccionar todas las marcas por defecto
                foreach (ListItem item in cblMarcas.Items)
                {
                    item.Selected = true;
                }
            }
            catch (Exception ex)
            {
                MostrarError("Error al cargar las marcas: " + ex.Message);
            }
        }

        private void CargarProductos(string orden = "precio_asc")
        {
            try
            {
                string nombre = txtNombreFiltro.Text.Trim();
                decimal? precioMin = string.IsNullOrEmpty(txtPrecioMin.Text) ? null : (decimal?)decimal.Parse(txtPrecioMin.Text);
                decimal? precioMax = string.IsNullOrEmpty(txtPrecioMax.Text) ? null : (decimal?)decimal.Parse(txtPrecioMax.Text);

                List<int> marcasSeleccionadas = cblMarcas.Items
                    .Cast<ListItem>()
                    .Where(item => item.Selected)
                    .Select(item => int.Parse(item.Value))
                .ToList();

                var client = new ProductoClient();
                var productos = client.ListarTodos();

                productosContainer.Controls.Clear();


                // Generar tarjetas de productos
                foreach (var producto in productos)
                {
                    HtmlGenericControl col = new HtmlGenericControl("div");
                    col.Attributes.Add("class", "col-md-4 col-lg-3 mb-4");

                    HtmlGenericControl card = new HtmlGenericControl("div");
                    card.Attributes.Add("class", "card h-100 shadow-sm");

                    // Imagen del producto
                    HtmlGenericControl img = new HtmlGenericControl("img");
                    img.Attributes.Add("class", "card-img-top p-3");
                    //img.Attributes.Add("src", producto.ImagenUrl);
                    img.Attributes.Add("alt", producto.nombre);
                    img.Style.Add("height", "180px");
                    img.Style.Add("object-fit", "contain");
                    card.Controls.Add(img);

                    HtmlGenericControl cardBody = new HtmlGenericControl("div");
                    cardBody.Attributes.Add("class", "card-body d-flex flex-column");

                    // Nombre del producto
                    HtmlGenericControl h5 = new HtmlGenericControl("h5");
                    h5.Attributes.Add("class", "card-title");
                    h5.InnerText = producto.nombre;
                    cardBody.Controls.Add(h5);

                    // Marca
                    HtmlGenericControl marca = new HtmlGenericControl("p");
                    marca.Attributes.Add("class", "text-muted small");
                    marca.InnerText = producto.marca;
                    cardBody.Controls.Add(marca);

                    // Precio
                    HtmlGenericControl precio = new HtmlGenericControl("h6");
                    precio.Attributes.Add("class", "text-success mt-auto");
                    precio.InnerText = $"S/{producto.precio.ToString("N2")}";
                    cardBody.Controls.Add(precio);

                    // Botón
                    HtmlGenericControl btnContainer = new HtmlGenericControl("div");
                    btnContainer.Attributes.Add("class", "d-grid gap-2 mt-2");

                    // Crear botón tipo link (HtmlAnchor)
                    HtmlAnchor btn = new HtmlAnchor();
                    btn.Attributes.Add("class", "btn btn-purple");

                    string id = producto.idProducto.ToString();

                    // Redirección directa (misma pantalla)
                    btn.HRef = $"/PaginasCliente/DetalleProducto.aspx?id={id}";
                    btn.InnerText = "Ver detalle";

                    btnContainer.Controls.Add(btn);
                    cardBody.Controls.Add(btnContainer);
                    card.Controls.Add(cardBody);
                    col.Controls.Add(card);
                    productosContainer.Controls.Add(col);
                }
            }
            catch (Exception ex)
            {
                MostrarError("Error al cargar los productos: " + ex.Message);
            }
        }

        protected void btnAplicarFiltros_Click(object sender, EventArgs e)
        {
            // En esta versión hardcodeada, los filtros no tendrán efecto real
            // pero se mantiene la estructura para demostración
            CargarProductos();
        }

        protected void btnLimpiarFiltros_Click(object sender, EventArgs e)
        {
            txtNombreFiltro.Text = "";
            txtPrecioMin.Text = "";
            txtPrecioMax.Text = "";

            foreach (ListItem item in cblMarcas.Items)
            {
                item.Selected = true; // Seleccionar todas al limpiar
            }

            CargarProductos();
        }

        protected void lnkOrdenar_Click(object sender, EventArgs e)
        {
            LinkButton lnk = (LinkButton)sender;
            CargarProductos(lnk.CommandArgument);
        }

        private void MostrarError(string mensaje)
        {
            // Implementación simple para mostrar errores
            ScriptManager.RegisterStartupScript(this, GetType(), "showError",
                $"alert('{mensaje}');", true);
        }
    }
}