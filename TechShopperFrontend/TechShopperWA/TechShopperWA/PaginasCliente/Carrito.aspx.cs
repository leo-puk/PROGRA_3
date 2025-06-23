using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI.WebControls;
using TechShopperWA.CarritoItemsWS;
using TechShopperWA.CarritosWS;
using TechShopperWA.ProductosWS;

namespace TechShopperWA.PaginasCliente
{
    public partial class Carrito : System.Web.UI.Page
    {
        //AGREGAR DTOs DE LOS WS
        //private CarritosWS.carritoDTO carrito;
        //private List<carritoItemsDTO> items;

        //hardcode (eliminar)
        public class ProductoDTO_
        {
            private int idProducto;
            private Double precio;
            private int stockDisponible;
            private int stockReservado;
            private int stockMinimo;
            private String nombre;
            private String marca;
            private String descripcion;
            private UsuarioDTO_ usuario;

            public ProductoDTO_(int idProducto, double precio, string nombre, string marca, string descripcion)
            {
                this.idProducto = idProducto;
                this.precio = precio;
                this.nombre = nombre;
                this.marca = marca;
                this.descripcion = descripcion;
            }

            public int IdProducto { get => idProducto; set => idProducto = value; }
            public double Precio { get => precio; set => precio = value; }
            public int StockDisponible { get => stockDisponible; set => stockDisponible = value; }
            public int StockReservado { get => stockReservado; set => stockReservado = value; }
            public int StockMinimo { get => stockMinimo; set => stockMinimo = value; }
            public string Nombre { get => nombre; set => nombre = value; }
            public string Marca { get => marca; set => marca = value; }
            public string Descripcion { get => descripcion; set => descripcion = value; }
            public UsuarioDTO_ Usuario { get => usuario; set => usuario = value; }
        }
        public class UsuarioDTO_
        {
            private int idUsuario;
            private string nombre;

            public UsuarioDTO_(int idUsuario, string nombre)
            {
                this.IdUsuario = idUsuario;
                this.Nombre = nombre;
            }

            public int IdUsuario { get => idUsuario; set => idUsuario = value; }
            public string Nombre { get => nombre; set => nombre = value; }
        }
        public class ClienteDTO_
        {
            private string direccion;
            private string telefono;
            private string infoTarjetaCredito;
            private string infoCompra;
            private double balanceCuenta;

            public string Direccion { get => direccion; set => direccion = value; }
            public string Telefono { get => telefono; set => telefono = value; }
            public string InfoTarjetaCredito { get => infoTarjetaCredito; set => infoTarjetaCredito = value; }
            public string InfoCompra { get => infoCompra; set => infoCompra = value; }
            public double BalanceCuenta { get => balanceCuenta; set => balanceCuenta = value; }
        }
        public class CarritoDTO_
        {
            private int idCarrito;
            private double precio;
            private UsuarioDTO_ usuario;
            public CarritoDTO_()
            { 
            }
            public CarritoDTO_(int idCarrito, double precio)
            {
                this.IdCarrito = idCarrito;
                this.Precio = precio;
            }

            public int IdCarrito { get => idCarrito; set => idCarrito = value; }
            public double Precio { get => precio; set => precio = value; }
            public UsuarioDTO_ Usuario { get => usuario; set => usuario = value; }
        }
        public class CarritoItem_
        {
            private int idCarritoItems;
            private CarritoDTO_ carrito;
            private ProductoDTO_ producto;
            private int cantidad;
            private Double precioUnitario;
            private DateTime fechaRegistro;

            public CarritoItem_(int idCarritoItems, ProductoDTO_ producto, int cantidad, double precioUnitario)
            {
                this.idCarritoItems = idCarritoItems;
                this.producto = producto;
                this.cantidad = cantidad;
                this.precioUnitario = precioUnitario;
            }

            public int IdCarritoItems { get => idCarritoItems; set => idCarritoItems = value; }
            public CarritoDTO_ Carrito { get => carrito; set => carrito = value; }
            public ProductoDTO_ Producto { get => producto; set => producto = value; }
            public int Cantidad { get => cantidad; set => cantidad = value; }
            public double PrecioUnitario { get => precioUnitario; set => precioUnitario = value; }
            public DateTime FechaRegistro { get => fechaRegistro; set => fechaRegistro = value; }
        }
        //fin de hardcode



        protected void Page_Init(object sender, EventArgs e)
        {
            if (Session["Acceso"] != null)
            {
                //se carga el carrito a esta pantalla
                CargarCarrito();
            } else
            {
                //una señal de que si quiero comprar debes iniciar sesión
                //mostrarAlertaDeseaIniciarSesion();
                Response.Redirect("../InicionSesion/IniciarSesion.aspx");
            }
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarCarrito();
            }
        }

        private void CargarCarrito()
        {

            //sería CARGAR EL CARRITO DESDE EL Session["Carrito"]
            //mostrarItems();

            //hardcode
            CarritoDTO_ carrito = new CarritoDTO_();
            carrito.IdCarrito = 1;
            carrito.Precio = 100;
            carrito.Usuario = new UsuarioDTO_(777, "cliente");

            mostrarItems(carrito.IdCarrito);


        }

        private void mostrarItems(int idCarrito)
        {
            // Simular algunos productos en el carrito
            var producto1 = new ProductoDTO_(1, 5, "Laptop", "LG", "Laptop de alto rendimiento");
            var producto2 = new ProductoDTO_(2, 3.5, "Mouse", "Logitech", "Mouse inalámbrico");

            var item1 = new CarritoItem_(1, producto1, 2, 5);   // Total = 10
            var item2 = new CarritoItem_(2, producto2, 1, 3.5); // Total = 3.5

            var lista = new List<dynamic>
{
    new { Id = 1, Nombre = "Laptop", Precio = 5.0, Cantidad = 2, ImagenUrl = "https://via.placeholder.com/120" },
    new { Id = 2, Nombre = "Mouse", Precio = 3.5, Cantidad = 1, ImagenUrl = "https://via.placeholder.com/120" }
};

            rptCarrito.DataSource = lista;
            rptCarrito.DataBind();

            double total = lista.Sum(i => (double)i.Precio * (int)i.Cantidad);
            lblTotal.Text = $"Total: ${total:N2}";

        }


        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            //este es para eliminar un item del carrito
            var btn = (System.Web.UI.WebControls.Button)sender;
            int id = int.Parse(btn.CommandArgument);



            CargarCarrito();
        }

        protected void btnHacerPedido_Click(object sender, EventArgs e)
        {
            
           //mandar a siguiente pestaña (pedido)
        }

        protected void btnMas_Click(object sender, EventArgs e)
        {
            var btn = (Button)sender;
            int id = int.Parse(btn.CommandArgument);

            // Aquí aumentarías la cantidad del producto con ese ID
            // Simulado por ahora
            Response.Write($"Aumentar cantidad del producto {id}");

            CargarCarrito(); // Recargar el frontend
        }

        protected void btnMenos_Click(object sender, EventArgs e)
        {
            var btn = (Button)sender;
            int id = int.Parse(btn.CommandArgument);

            // Aquí disminuirías la cantidad del producto con ese ID
            // Simulado por ahora
            Response.Write($"Disminuir cantidad del producto {id}");

            CargarCarrito(); // Recargar el frontend
        }

    }
}
