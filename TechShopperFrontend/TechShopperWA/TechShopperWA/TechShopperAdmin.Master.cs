using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace TechShopperWA
{
	public partial class Site1 : System.Web.UI.MasterPage
	{
		protected void Page_Load(object sender, EventArgs e)
		{

            

    }
        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            // Limpia la sesión
            Session.Clear();
            Session.Abandon();

            // Redirige al login o página principal
            Response.Redirect("/InicionSesion/IniciarSesion.aspx");
        }
    }
}