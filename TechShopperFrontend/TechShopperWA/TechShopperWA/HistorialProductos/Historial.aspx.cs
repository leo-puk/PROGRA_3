using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperBO;
using TechShopperBO.MovimientosStockWS;

namespace TechShopperWA
{
    public partial class ReporteVentas : System.Web.UI.Page
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
                CargarMovimientos();
            }

        }

        private void CargarMovimientos()
        {
            MovimientoStockClient client = new MovimientoStockClient();
            List<movimientoStockDTO> movimientos = client.ListarMovimientos();

            if (movimientos != null && movimientos.Count > 0)
            {
                gvMovimientos.DataSource = movimientos;
                gvMovimientos.DataBind();
            }
            else
            {
                gvMovimientos.DataSource = null;
                gvMovimientos.DataBind();

            }
        }

        protected void gvMovimientos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvMovimientos.PageIndex = e.NewPageIndex;
            CargarMovimientos();
        }

    }
}