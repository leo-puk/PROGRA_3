using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace TechShopperWA.PaginasCliente
{
    public partial class Index : System.Web.UI.Page
    {
        // Clase modelo para Producto (hardcodeada)
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

        protected void Page_Load(object sender, EventArgs e)
        {
            Page.UnobtrusiveValidationMode = UnobtrusiveValidationMode.None;

            if (!IsPostBack)
            {
                CargarMarcas();
                CargarProductos();
            }
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
                // Obtener parámetros de filtro (aunque no se usarán realmente)
                string nombre = txtNombreFiltro.Text.Trim();
                decimal? precioMin = null;
                decimal? precioMax = null;

                if (!string.IsNullOrEmpty(txtPrecioMin.Text))
                    precioMin = decimal.Parse(txtPrecioMin.Text);

                if (!string.IsNullOrEmpty(txtPrecioMax.Text))
                    precioMax = decimal.Parse(txtPrecioMax.Text);

                // Obtener marcas seleccionadas (aunque no afectará los datos hardcodeados)
                List<int> marcasSeleccionadas = new List<int>();
                foreach (ListItem item in cblMarcas.Items)
                {
                    if (item.Selected)
                        marcasSeleccionadas.Add(int.Parse(item.Value));
                }

                // Datos hardcodeados de productos
                List<Producto> productos = new List<Producto>
                {
                    new Producto { Id = 1, Nombre = "Smartphone Galaxy S21", Marca = "Samsung", Precio = 899.99m, ImagenUrl = "https://via.placeholder.com/150?text=Galaxy+S21" },
                    new Producto { Id = 2, Nombre = "iPhone 13 Pro", Marca = "Apple", Precio = 999.99m, ImagenUrl = "https://via.placeholder.com/150?text=iPhone+13" },
                    new Producto { Id = 3, Nombre = "Monitor 27\" 4K", Marca = "LG", Precio = 349.99m, ImagenUrl = "https://via.placeholder.com/150?text=Monitor+4K" },
                    new Producto { Id = 4, Nombre = "Auriculares WH-1000XM4", Marca = "Sony", Precio = 279.99m, ImagenUrl = "https://via.placeholder.com/150?text=Sony+XM4" },
                    new Producto { Id = 5, Nombre = "Laptop Pavilion", Marca = "HP", Precio = 749.99m, ImagenUrl = "https://via.placeholder.com/150?text=HP+Pavilion" },
                    new Producto { Id = 6, Nombre = "Tablet Galaxy Tab S7", Marca = "Samsung", Precio = 499.99m, ImagenUrl = "https://via.placeholder.com/150?text=Tab+S7" },
                    new Producto { Id = 7, Nombre = "MacBook Air", Marca = "Apple", Precio = 999.99m, ImagenUrl = "https://via.placeholder.com/150?text=MacBook+Air" },
                    new Producto { Id = 8, Nombre = "Teclado Mecánico", Marca = "HP", Precio = 89.99m, ImagenUrl = "https://via.placeholder.com/150?text=Teclado+HP" }
                };

                // Aplicar ordenamiento hardcodeado
                switch (orden)
                {
                    case "precio_asc":
                        productos = productos.OrderBy(p => p.Precio).ToList();
                        break;
                    case "precio_desc":
                        productos = productos.OrderByDescending(p => p.Precio).ToList();
                        break;
                    case "nombre_asc":
                        productos = productos.OrderBy(p => p.Nombre).ToList();
                        break;
                    case "nombre_desc":
                        productos = productos.OrderByDescending(p => p.Nombre).ToList();
                        break;
                }

                // Limpiar contenedor
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
                    img.Attributes.Add("src", producto.ImagenUrl);
                    img.Attributes.Add("alt", producto.Nombre);
                    img.Style.Add("height", "180px");
                    img.Style.Add("object-fit", "contain");
                    card.Controls.Add(img);

                    HtmlGenericControl cardBody = new HtmlGenericControl("div");
                    cardBody.Attributes.Add("class", "card-body d-flex flex-column");

                    // Nombre del producto
                    HtmlGenericControl h5 = new HtmlGenericControl("h5");
                    h5.Attributes.Add("class", "card-title");
                    h5.InnerText = producto.Nombre;
                    cardBody.Controls.Add(h5);

                    // Marca
                    HtmlGenericControl marca = new HtmlGenericControl("p");
                    marca.Attributes.Add("class", "text-muted small");
                    marca.InnerText = producto.Marca;
                    cardBody.Controls.Add(marca);

                    // Precio
                    HtmlGenericControl precio = new HtmlGenericControl("h6");
                    precio.Attributes.Add("class", "text-success mt-auto");
                    precio.InnerText = $"${producto.Precio.ToString("N2")}";
                    cardBody.Controls.Add(precio);

                    // Botón
                    HtmlGenericControl btnContainer = new HtmlGenericControl("div");
                    btnContainer.Attributes.Add("class", "d-grid gap-2 mt-2");

                    HtmlAnchor btn = new HtmlAnchor();
                    btn.Attributes.Add("class", "btn btn-purple");
                    btn.HRef = $"#"; // Link temporal
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