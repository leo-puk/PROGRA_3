using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperBO;
using TechShopperBO.ClientesWS;
using TechShopperBO.ProductosWS;

namespace TechShopperWA.PaginasCliente
{
	public partial class ResumenPedido : System.Web.UI.Page
	{
        private List<carritoItemsDTOSoap> carritoItems;
        private TechShopperBO.ClientesWS.usuarioDTO usuarioDto;
		protected void Page_Load(object sender, EventArgs e)
		{
            
                if (Session["Usuario"] == null)
                {
                    Response.Redirect("~/InicionSesion/IniciarSesion.aspx");
                    return;
                }
                
                CargarProductosCarrito();
                CargarDireccionEnvio();
                CalcularTotales();
            
        }

        private void CargarUsuario()
        {
            usuarioDto = (TechShopperBO.ClientesWS.usuarioDTO)Session["Ususario"];
        }

        private void CargarProductosCarrito()
        {
            // Se cargan los productos
            carritoItems = (List<carritoItemsDTOSoap>)Session["Carrito"];

            // Si el usuario inició sesión se buscan sus items de acuerdo a su id y se muestran
            var usuario = Session["Usuario"] as TechShopperBO.ClientesWS.usuarioDTO;
            usuarioDto = usuario;
            if (usuario != null)
            {
                var carritoBO = new ClienteClient();
                var itemsCarrito = carritoBO.MostrarCarritoDeCliente(usuario.idUsuario);
                var productoBO = new ProductoClient();

                // Crear lista para almacenar los productos a mostrar
                List<dynamic> productosAMostrar = new List<dynamic>();

                // Parte iterativa para cada item en el carrito
                foreach (var item in carritoItems)
                {
                    productoDTO productoDto = productoBO.ObtenerPorId(item.idProducto);

                    // Creación de un producto a mostrar y agregarlo a la lista
                    productosAMostrar.Add(new
                    {
                        ImagenUrl = productoDto.imagenURL,
                        Cantidad = item.cantidad,
                        Nombre = productoDto.nombre,
                        PrecioUnitario = productoDto.precio
                    });
                }

                // Asignar la lista como fuente de datos y enlazar
                rptProductos.DataSource = productosAMostrar;
                rptProductos.DataBind();

                litCantidadProductos.Text = productosAMostrar.Count.ToString();
            }
        }

        private void CargarDireccionEnvio()
        {
            //placeholder para mostrar la Session["Usuario"]
            var usuario = Session["Usuario"] as TechShopperBO.ClientesWS.usuarioDTO;

            var cliente = new ClienteClient();
            var clienteDto = cliente.ObtenerClientePorId((int)Session["IdUsuario"]);
            if (usuario != null)
            {
                lblNombreCliente.InnerText = $"{usuario.nombre}";
                txtDireccion.Text = $"{clienteDto.direccion}";
                lblTelefono.InnerText = $"{clienteDto.telefono}";
            }
        }

        private void CalcularTotales()
        {
            //se comprueba que se inició sesión
            var usuario = Session["Usuario"] as TechShopperBO.ClientesWS.usuarioDTO;
            if (usuario != null)
            {
                //se calcula el subtotal de los items
                double subtotal = carritoItems.Sum(item => item.precioUnitario * item.cantidad);
                double envio = 7.77; // Valor fijo por ahora
                double descuento = 0.00; // Podrías calcular descuentos si los hay
                double total = subtotal + envio - descuento;

                lblSubtotal.InnerText = $"S/ {subtotal:N2}";
                lblEnvio.InnerText = $"S/ {envio:N2}";
                lblDescuento.InnerText = $"- S/ {descuento:N2}";
                lblTotal.InnerText = $"S/ {total:N2}";
            }
        }

        protected void btnValidarDireccion_Click(object sender, EventArgs e)
        {
            string direccion = txtDireccion.Text;
            var clienteBo = new ClienteClient();
            clienteDTO clienteNuevo = new clienteDTO();
            clienteDTO clienteActual= new clienteDTO();
            clienteActual = clienteBo.ObtenerClientePorId(usuarioDto.idUsuario);
            clienteNuevo = clienteActual;
            clienteNuevo.direccion = direccion;


            //validar al intentar crear una localización de la dirección introducida


            var localizacion_ = new LocalizacionClient();
            int res = localizacion_.insertarLocalizacion(direccion, -12.0464, -77.0494, usuarioDto.idUsuario);
            if (res>0)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('La dirección no es válida. Asegurese que sea una dirección real de Lima.');", true);
            } else
            {
                clienteBo.ActualizarCliente(clienteNuevo);
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('La dirección es valida y se guardó :D');", true);
            }

        }
        protected void btnCambiarDireccion_Click(object sender, EventArgs e)
        {
            //Response.Redirect("~/Cuenta/MiPerfil.aspx");
        }

        protected void btnFinalizarPedido_Click(object sender, EventArgs e)
        {

            //SOLO PEDIDOS A DIRECCONES DE LIMA
            //D:

            var usuarioDto_ = Session["Usuario"];
            //hacer listado de id de los items
            List<int> itemsSeleccionados = new List<int>();
            if (carritoItems != null)
            {
                itemsSeleccionados = carritoItems.Select(item => item.idCarritoItems).ToList();
            }


            //utilizar función comprar Pedido. 
            var clientebo = new ClienteClient();
            int resultado = clientebo.RealizarCompra(usuarioDto.idUsuario, itemsSeleccionados);
            //-1: los datos de idusuario estan mal
            //-2: al menos uno de los items seleccionado no existen
            //- 3: "el usuario ni si quiera tiene carrito"
            //- 4: "item no pertenece al carrito del usuario"
            //- 5: "no hay stock suficiente"
            //- 6: "no existe localizacion de usuario."
            switch (resultado)
            {
                case -1:
                    Response.Write("los datos del usuario estan mal " + usuarioDto.idUsuario);

                    break;
                case -2:
                    Response.Write("al menos uno de los items no existe");
                    break;
                case -3:
                    Response.Write("el usuario no tiene carrito");
                    break;
                case -4:
                    Response.Write("item no pertenece al carrito del usuario");
                    break;
                case -5:
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta", "alert('no hay suficiente stock del producto');", true);
                    break;
                case -6:
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta", "alert('no existe localización');", true);
                    break;
                default:
                    Response.Write("compra realizada");

                    int idCliente = (int)Session["IdUsuario"];
                    clientebo.EnviarCorreoPedido(idCliente);

                    break;

            }



            //mostrar una pantalla de listo, se realizó el pago.
            mostrarPantallaDePago();
            ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('Compra exitosa');", true);
            Response.Redirect("/PaginasCliente/VistaProductosCliente.aspx");
        }

        private void mostrarPantallaDePago()
        {
            ScriptManager.RegisterStartupScript(this, this.GetType(), "alerta",
                    "alert('PEDIDO REALIZADO :D');", true);
        }
    }
}